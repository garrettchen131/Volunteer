package cn.sicnu.common.feign.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class AuthClientConfig {

    @Value("appId")
    private String clientId;
    @Value("123456")
    private String secret;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(clientId, secret);
    }

}
