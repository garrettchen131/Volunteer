package cn.sicnu.third.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.utils.ResultInfoUtil;
import cn.sicnu.third.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ContractResource {
    public final ContractService contractService;

    @PostMapping("/contract/{idCard}")
    public ResultInfo<Void> signAccount(@PathVariable("idCard") String idCard) {
        contractService.signAccount(idCard);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }

    @GetMapping("/contract/{idCard}")
    public ResultInfo<Integer> getScore(@PathVariable("idCard") String idCard) throws ContractException {
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), contractService.getScore(idCard));
    }

    @PostMapping("/contract/{idCard}/{score}")
    public ResultInfo<Void> addScore(
            @PathVariable("idCard") String idCard,
            @PathVariable long score
    ) throws ContractException {
        contractService.addScore(idCard, score);
        return ResultInfoUtil.buildSuccess(getCurrentUrl());
    }
}
