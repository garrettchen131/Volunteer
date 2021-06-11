package cn.sicnu.common.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class RequestUtil {

    public static Optional<HttpServletRequest> getCurrentRequest() {
        return Optional.ofNullable(
                (ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())
        ).map(ServletRequestAttributes::getRequest);
    }

    public static String getCurrentUrl() {
        return getCurrentRequest().map(HttpServletRequest::getServletPath).orElse("");
    }

    public static String getCurrentUri() {
        return getCurrentRequest().map(HttpServletRequest::getRequestURI).orElse("");
    }

    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
