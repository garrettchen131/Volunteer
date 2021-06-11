package cn.sicnu.third.config;

import cn.hutool.extra.mail.MailAccount;
import lombok.Data;
import lombok.val;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@ConfigurationProperties(prefix = "mail.server")
@Data
public class MailAccountConfig {

    private String host;
    private int port;
    private String username;
    private String password;

    @Bean
    public MailAccount mailAccount() {
        val mailAccount = new MailAccount();
        mailAccount.setAuth(true);
        mailAccount.setCharset(StandardCharsets.UTF_8);
        mailAccount.setHost(host);
        mailAccount.setPort(port);
        mailAccount.setFrom(username);
        mailAccount.setUser(username);
        mailAccount.setPass(password);
        return mailAccount;
    }

}
