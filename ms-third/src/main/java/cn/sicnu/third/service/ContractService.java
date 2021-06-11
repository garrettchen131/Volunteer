package cn.sicnu.third.service;

import cn.sicnu.third.contract.ScoreContract;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractService {

    private final ScoreContract scoreContract;

    public void signAccount(String idCard) {
        scoreContract.signAccount(
                idCard,
                new TransactionCallback() {
                    @Override
                    public void onResponse(TransactionReceipt receipt) {
                        log.info(idCard + " sign : " + receipt.toString());
                    }
                }
        );
    }

    public int getScore(String idCard) throws ContractException {
        return scoreContract.getScore(idCard).intValue();
    }

    public void addScore(String idCard, long score) {
        scoreContract.addScore(
                idCard,
                BigInteger.valueOf(score),
                new TransactionCallback() {
                    @Override
                    public void onResponse(TransactionReceipt receipt) {
                        log.info(idCard + " sign : " + receipt.toString());
                    }
                }
        );
    }
}
