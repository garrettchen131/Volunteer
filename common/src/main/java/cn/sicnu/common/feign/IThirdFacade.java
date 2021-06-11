package cn.sicnu.common.feign;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.utils.ResultInfoUtil;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@FeignClient(value = "ms-third")
public interface IThirdFacade {
    @GetMapping("/code/email/{email}")
    ResultInfo<String> getEmailCode(@PathVariable("email") String email);


    @GetMapping("/code/phone/{phone}")
    ResultInfo<String> getPhoneCode(@PathVariable("phone") String phone);

    @PostMapping("/contract/{idCard}")
    ResultInfo<Void> signAccount(@PathVariable("idCard") String idCard);

    @GetMapping("/contract/{idCard}")
    ResultInfo<Integer> getScore(@PathVariable("idCard") String idCard);

    @PostMapping("/contract/{idCard}/{score}")
    ResultInfo<Void> addScore(
            @PathVariable("idCard") String idCard,
            @PathVariable("score") long score
    );


}
