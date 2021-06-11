package cn.sicnu.oauth.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

/**
 * Oauth2 控制器
 */
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthResource {

    private final TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public ResultInfo<Map<String, Object>> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    /**
     * 自定义 Token 返回对象
     *
     * @param accessToken
     * @return
     */
    private ResultInfo<Map<String, Object>> custom(OAuth2AccessToken accessToken) {
        val token = (DefaultOAuth2AccessToken) accessToken;
        val data = new LinkedHashMap<>(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        data.put("expireIn", token.getExpiresIn());
        data.put("scopes", token.getScope());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), data);
    }

}