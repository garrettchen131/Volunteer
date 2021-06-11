package cn.sicnu.school.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

/**
 * Rest 配置类
 */
@Configuration
@RequiredArgsConstructor
public class RestTemplateConfiguration {

    private final OAuth2ClientConfiguration oAuth2ClientConfiguration;

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        val restTemplate = new RestTemplate();
        restTemplate.getInterceptors()
                .add(new BasicAuthenticationInterceptor(
                        oAuth2ClientConfiguration.getClientId(),
                        oAuth2ClientConfiguration.getSecret()
                ));
        return restTemplate;
    }

}