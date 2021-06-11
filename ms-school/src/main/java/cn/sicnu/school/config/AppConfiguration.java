package cn.sicnu.school.config;

import cn.sicnu.school.filter.UserInfoFilter;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final UserInfoFilter userInfoFilter;

    @Bean
    public FilterRegistrationBean userInfoFilterRegistrationBean() {
        val filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(userInfoFilter);
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/*"));
        filterRegistrationBean.setName("userInfoFilter");
        return filterRegistrationBean;
    }

}
