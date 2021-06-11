package cn.sicnu.oauth.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.dto.AccountDTO;
import cn.sicnu.common.model.enums.Roles;
import cn.sicnu.common.model.vo.AccountVO;
import cn.sicnu.common.utils.ResultInfoUtil;
import cn.sicnu.oauth.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountResource {

    private final AccountService accountService;

    @GetMapping("/info/{accountId}")
    public ResultInfo<AccountVO> getAccountInfo(@PathVariable("accountId") long accountId) {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), accountService.getAccountInfo(accountId));
    }


    @PostMapping("/register/{role}")
    public ResultInfo<Long> register(@RequestBody AccountDTO accountDTO, @PathVariable String role) {
        val autoId = accountService.register(accountDTO, Roles.valueOf(role.toUpperCase()).name());
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), autoId);
    }

    @GetMapping("/check")
    public ResultInfo<Void> checkAccountIsExist(@RequestBody AccountDTO accountDTO) {
        accountService.checkAccountIsExist(accountDTO);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

    @GetMapping("/check/email/{email}")
    public ResultInfo<Void> checkEmailIsExist(@PathVariable("email") String email) {
        accountService.checkEmailIsExist(email);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

    @GetMapping("/check/phone/{phone}")
    public ResultInfo<Void> checkPhoneIsExist(@PathVariable("phone") String phone) {
        accountService.checkPhoneIsExist(phone);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }
}
