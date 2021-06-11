package cn.sicnu.third.service;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class MailService {
    private final MailAccount mailAccount;

    public void sendCode(String code, String email) {
        send("大学生志愿服务平台验证码", "验证码：".concat(code).concat("，有效时间 5分钟"), email);
    }

    public void send(String title, String message, String email) {
        MailUtil.send(mailAccount, Collections.singleton(email), title, message, false);
    }
}
