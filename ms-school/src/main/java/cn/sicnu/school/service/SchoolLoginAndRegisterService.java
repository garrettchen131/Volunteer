package cn.sicnu.school.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.sicnu.common.constant.ApiConstant;
import cn.sicnu.common.feign.IOAuthFacade;
import cn.sicnu.common.feign.IThirdFacade;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.AccountDTO;
import cn.sicnu.common.model.dto.SchoolDTO;
import cn.sicnu.common.model.entity.School;
import cn.sicnu.common.model.enums.Roles;
import cn.sicnu.common.utils.AssertUtil;
import cn.sicnu.common.utils.ResultInfoUtil;
import cn.sicnu.school.config.OAuth2ClientConfiguration;
import cn.sicnu.school.mapper.ISchoolMapper;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

/**
 * 食客服务业务逻辑层
 */
@Service
@RequiredArgsConstructor
public class SchoolLoginAndRegisterService {

    private final IOAuthFacade oAuthFacade;

    private final IThirdFacade thirdFacade;

    private final OAuth2ClientConfiguration oAuth2ClientConfiguration;

    private final ISchoolMapper schoolMapper;

    private final RedisTemplate redisTemplate;


    /**
     * 登录
     *
     * @param account  帐号：用户名或手机或邮箱
     * @param password 密码
     * @return
     */
    public ResultInfo signIn(String account, String password) {
        // 构建请求体（请求参数）
        val body = Maps.<String, String>newHashMap();
        body.put("username", account);
        body.put("password", password);
        body.put("grant_type", oAuth2ClientConfiguration.getGrant_type());
        body.put("scope", oAuth2ClientConfiguration.getScope());

        var resultInfo = oAuthFacade.postAccessToken(body);
        resultInfo = resultInfo.withPath(getCurrentUrl());
        // 处理返回结果
        if (resultInfo.getCode() != ApiConstant.SUCCESS_CODE) {
            // 登录失败
            return resultInfo;
        }
        val data = (Map<String, Object>) resultInfo.getData();
        val accountId = ((int) data.get("accountId"));
        val accessToken = "Bearer "+ data.get("accessToken");
        val expireIn = (int) data.get("expireIn");
        School school = schoolMapper.selectSchoolByAccountId(accountId);
        redisTemplate.opsForValue().set(accessToken, school, expireIn, TimeUnit.SECONDS);
        return resultInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo schoolRegister(SchoolDTO schoolDTO) {
        //验证验证码
        val emailCode = thirdFacade.getEmailCode(schoolDTO.getEmail()).getData();
        AssertUtil.isTrue(StrUtil.isBlank(emailCode), "验证码不存在或已失效");
        if (!StrUtil.equals(emailCode, schoolDTO.getVerifyCode(), true)) {
            return ResultInfoUtil.buildError("验证码错误", getCurrentUrl());
        }
        val accountDTO = new AccountDTO();
        BeanUtil.copyProperties(schoolDTO, accountDTO, false);
        val school = new School();
        BeanUtil.copyProperties(schoolDTO, school, false);

        val resultInfo = oAuthFacade.register(accountDTO, Roles.SCHOOL.name());
        if (resultInfo.getCode() != ApiConstant.SUCCESS_CODE) {
            return resultInfo.withPath(getCurrentUrl());
        }
        school.setAccountId(Long.parseLong(String.valueOf(resultInfo.getData())));
        schoolMapper.save(school);
        return signIn(schoolDTO.getUsername(), schoolDTO.getPassword())
                .withPath(getCurrentUrl());
    }
}
