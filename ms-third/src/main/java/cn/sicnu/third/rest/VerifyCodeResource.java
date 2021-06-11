package cn.sicnu.third.rest;

import cn.hutool.core.lang.Validator;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.third.service.SendVerifyCodeService;
import cn.sicnu.common.utils.AssertUtil;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.*;


import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@RequiredArgsConstructor
@Slf4j
public class VerifyCodeResource {

    private final SendVerifyCodeService sendVerifyCodeService;


    @PostMapping("/code/phone/{phone}")
    public ResultInfo<Void>sendByPhone(@PathVariable("phone") String phone) {
        AssertUtil.isNotEmpty(phone, "手机号不能为空");
        AssertUtil.isTrue(!Validator.isMobile(phone), "请输入正确的手机号");
        return sendVerifyCodeService.sendByPhone(phone.trim());
    }

    @PostMapping("/code/email/{email}")
    public ResultInfo<Void> sendByEmail(@PathVariable("email") String email) {
        AssertUtil.isNotEmpty(email, "邮箱不能为空");
        AssertUtil.isTrue(!Validator.isEmail(email), "请输入正确的邮箱");
        return sendVerifyCodeService.sendByEmail(email.trim());
    }

    @GetMapping("/code/email/{email}")
    public ResultInfo<String> getEmailCode(@PathVariable("email") String email) {
        val code = sendVerifyCodeService.getCodeByEmail(email);
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), code);
    }

    @GetMapping("/code/phone/{phone}")
    public ResultInfo<String> getPhoneCode(@PathVariable("phone") String phone) {
        val code = sendVerifyCodeService.getCodeByPhone(phone);
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), code);
    }


}
