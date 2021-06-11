package cn.sicnu.third.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.sicnu.common.constant.ApiConstant;
import cn.sicnu.common.constant.RedisKeyConstant;
import cn.sicnu.common.exception.ParameterException;
import cn.sicnu.common.feign.IOAuthFacade;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

/**
 * 发送验证码业务逻辑层
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SendVerifyCodeService {

    private final StringRedisTemplate redisTemplate;

    private final MailService mailService;

    private final IOAuthFacade oAuthFacade;


    public ResultInfo<Void> sendByEmail(String email) {
        val resultInfo = oAuthFacade.checkEmailIsExist(email);
        if (resultInfo.getCode() != ApiConstant.SUCCESS_CODE) {
            return resultInfo.withPath(getCurrentUrl());
        }
        if (!checkCodeIsEmailExpired(email)) {
            return ResultInfo.<Void>builder()
                    .code(ApiConstant.SUCCESS_CODE)
                    .message("请勿重复发送")
                    .build();
        }
        // 生成 6 位验证码
        val code = RandomUtil.randomString(6);
        try {
            mailService.sendCode(code, email);
        } catch (RuntimeException e) {
            log.error("邮箱发生发生错误 {}", e);
            throw new ParameterException("检查邮箱是否正确合法");
        }
        val key = RedisKeyConstant.verify_mail_code.getKey() + email;
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

    /**
     * 发送验证码
     *
     * @param phone
     */
    public ResultInfo<Void> sendByPhone(String phone) {
        val resultInfo = oAuthFacade.checkPhoneIsExist(phone);
        if (resultInfo.getCode() != ApiConstant.SUCCESS_CODE) {
            return resultInfo.withPath(getCurrentUrl());
        }
        // 根据手机号查询是否已生成验证码，已生成直接返回
        if (!checkCodeIsPhoneExpired(phone)) {
            return ResultInfo.<Void>builder().code(ApiConstant.SUCCESS_CODE).message("请勿重复发送").build();
        }
        // 生成 6 位验证码
        val code = RandomUtil.randomNumbers(6);
        // 调用短信服务发送短信
        // 发送成功，将 code 保存至 Redis，失效时间 120s
        val key = RedisKeyConstant.verify_phone_code.getKey() + phone;
        redisTemplate.opsForValue().set(key, code, 120, TimeUnit.SECONDS);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

    /**
     * 根据手机号查询是否已生成验证码
     *
     * @param phone
     * @return
     */
    private boolean checkCodeIsPhoneExpired(String phone) {
        String key = RedisKeyConstant.verify_phone_code.getKey() + phone;
        String code = redisTemplate.opsForValue().get(key);
        return StrUtil.isBlank(code);
    }

    private boolean checkCodeIsEmailExpired(String email) {
        String key = RedisKeyConstant.verify_mail_code.getKey() + email;
        String code = redisTemplate.opsForValue().get(key);
        return StrUtil.isBlank(code);
    }

    /**
     * 根据手机号获取验证码
     *
     * @param phone
     * @return
     */
    public String getCodeByPhone(String phone) {
        return redisTemplate.opsForValue().get(RedisKeyConstant.verify_phone_code.getKey() + phone);
    }

    public String getCodeByEmail(String email) {
        return redisTemplate.opsForValue().get(RedisKeyConstant.verify_mail_code.getKey() + email);
    }

}
