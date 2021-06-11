package cn.sicnu.third;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "cn.sicnu.common.feign")
public class ThirdApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThirdApplication.class, args);
    }
}
