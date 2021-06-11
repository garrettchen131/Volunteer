package cn.sicnu.student.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.sicnu.common.exception.ParameterException;
import cn.sicnu.common.model.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserInfoFilter extends OncePerRequestFilter {

    private final RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        val servletPath = request.getServletPath();
        val pathMatcher = new AntPathMatcher();

        if (pathMatcher.match("/login", servletPath) || pathMatcher.match("/register", servletPath)) {
            filterChain.doFilter(request, response);
            return;
        }
        val authHeader = request.getHeader("Authorization");
        if (StrUtil.isBlank(authHeader)) {
            if (pathMatcher.match("/ms/**", servletPath) || pathMatcher.match("/common/**", servletPath)) {
                filterChain.doFilter(request, response);
            }
        }
        val userMapInfo = (Map) redisTemplate.opsForValue().get(authHeader);
        if (userMapInfo == null) {
            throw new ParameterException("UserInfo Not Found");
        }
        val student = new Student();
        BeanUtil.fillBeanWithMap(userMapInfo, student, false);
        log.info("userInfo {}", student);
        request.setAttribute("user", student);
        filterChain.doFilter(request, response);
    }
}
