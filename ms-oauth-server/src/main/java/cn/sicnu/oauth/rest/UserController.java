package cn.sicnu.oauth.rest;

import cn.sicnu.common.model.domain.ResultInfo;
import cn.sicnu.common.model.domain.SignInIdentity;
import cn.sicnu.common.model.vo.SignInStudentInfo;
import cn.sicnu.common.utils.ResultInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;

import static cn.sicnu.common.utils.RequestUtil.getCurrentUrl;

/**
 * 用户中心
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final RedisTokenStore redisTokenStore;

    @GetMapping("/me")
    public ResultInfo getCurrentUser(Authentication authentication) {
        // 获取登录用户的信息，然后设置
        val signInIdentity = (SignInIdentity) authentication.getPrincipal();
        // 转为前端可用的视图对象
        val studentInfo = new SignInStudentInfo();
        BeanUtils.copyProperties(signInIdentity, studentInfo);
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), studentInfo);
    }

    /**
     * 安全退出
     *
     * @param access_token
     * @param authorization
     * @return
     */
    @GetMapping("/logout")
    public ResultInfo logout(
            String authorization,
            @RequestParam(value = "access_token", required = false) String access_token,
            @RequestHeader(value = "Authorization", required = false) String access_token_header)
    {
        if (StringUtils.isBlank(access_token)) {
            access_token = access_token_header;
        }
        // 判断 access_token 是否为空，为空将 authorization 赋值给 access_token
        if (StringUtils.isBlank(access_token)) {
            access_token = authorization;
        }
        // 判断 authorization 是否为空
        if (StringUtils.isBlank(access_token)) {
            return ResultInfoUtil.buildSuccess(getCurrentUrl(), "退出成功");
        }
        // 判断 bearer token 是否为空
        if (access_token.toLowerCase().contains("bearer ".toLowerCase())) {
            access_token = access_token.toLowerCase().replace("bearer ", "");
        }
        // 清除 redis token 信息
        val oAuth2AccessToken = redisTokenStore.readAccessToken(access_token);
        if (oAuth2AccessToken != null) {
            redisTokenStore.removeAccessToken(oAuth2AccessToken);
            val refreshToken = oAuth2AccessToken.getRefreshToken();
            redisTokenStore.removeRefreshToken(refreshToken);
        }
        return ResultInfoUtil.buildSuccess(getCurrentUrl(), "退出成功");
    }

}
