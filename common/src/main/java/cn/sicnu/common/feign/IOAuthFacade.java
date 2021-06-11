package cn.sicnu.common.feign;

import cn.sicnu.common.feign.config.AuthClientConfig;
import cn.sicnu.common.model.dto.AccountDTO;
import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.vo.AccountVO;
import cn.sicnu.common.utils.ResultInfoUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@FeignClient(value = "ms-oauth2-server", configuration = AuthClientConfig.class)
public interface IOAuthFacade {
    @PostMapping("/oauth/token")
    ResultInfo<Map<String, Object>> postAccessToken(@RequestParam Map<String, String> parameters);

    @PostMapping("/account/register/{role}")
    ResultInfo<Long> register(@RequestBody AccountDTO accountDTO, @PathVariable("role") String student);

    @GetMapping("/account/check")
    ResultInfo<Void> checkAccountIsExist(@RequestBody AccountDTO accountDTO);

    @GetMapping("/account/check/email/{email}")
    ResultInfo<Void> checkEmailIsExist(@PathVariable("email") String email);

    @GetMapping("/account/check/phone/{phone}")
    ResultInfo<Void> checkPhoneIsExist(@PathVariable("phone") String phone);

    @GetMapping("/account/info/{accountId}")
    ResultInfo<AccountVO> getAccountInfo(@PathVariable("accountId") long accountId);

}
