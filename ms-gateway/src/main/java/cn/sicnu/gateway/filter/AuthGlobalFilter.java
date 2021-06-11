package cn.sicnu.gateway.filter;

import cn.sicnu.common.constant.ApiConstant;
import cn.sicnu.gateway.commponent.HandleException;
import cn.sicnu.gateway.config.IgnoreUrlsConfig;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import reactor.core.publisher.Mono;



/**
 * 网关全局过滤器
 */
@Component
@RequiredArgsConstructor
@Order(0)
public class AuthGlobalFilter implements GlobalFilter {

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    private final RestTemplate restTemplate;

    private final HandleException handleException;


    /**
     * 身份校验处理
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 判断当前的请求是否在白名单中
        val pathMatcher = new AntPathMatcher();
        val path = ((ServerWebExchangeDecorator)exchange).getDelegate().getRequest().getURI().getPath();
        if (ignoreUrlsConfig.getUrls()
                .stream()
                .anyMatch(url -> pathMatcher.match(url, path))
        ) {
            return chain.filter(exchange);
        }

        // 获取 access_token
        var access_token = exchange.getRequest().getQueryParams().getFirst("access_token");
        if (StringUtils.isBlank(access_token)) {
            val header = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (!StringUtils.isBlank(header)) {
                access_token = header.replace("Bearer ", "");
            }
        }
        // 判断 access_token 是否为空
        if (StringUtils.isBlank(access_token)) {
            return handleException.writeError(exchange, ApiConstant.NO_LOGIN_MESSAGE);
        }
        try {
            // 发送远程请求，验证 token
            val checkTokenUrl = "http://ms-oauth2-server/oauth/check_token?token=";
            val entity = restTemplate.getForEntity(checkTokenUrl.concat(access_token), String.class);
            // token 无效的业务逻辑处理
            if (entity.getStatusCode() != HttpStatus.OK) {
                return handleException.writeError(exchange,
                        "Token was not recognised, token: ".concat(access_token));
            }
            if (StringUtils.isBlank(entity.getBody())) {
                return handleException.writeError(exchange,
                        "This token is invalid: ".concat(access_token));
            }
        } catch (Exception e) {
            return handleException.writeError(exchange,
                    "Token was not recognised, token: ".concat(access_token));
        }
        // 放行
        return chain.filter(exchange);
    }
}
