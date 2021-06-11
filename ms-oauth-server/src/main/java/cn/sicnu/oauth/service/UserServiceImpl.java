package cn.sicnu.oauth.service;

import cn.sicnu.common.model.domain.SignInIdentity;
import cn.sicnu.oauth.mapper.IAccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {

    private final IAccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        // 初始化登录认证对象
        val signInIdentity = new SignInIdentity();
        // 拷贝属性
        accountMapper
                .selectByAccountInfo(account)
                .ifPresentOrElse(
                        identity -> BeanUtils.copyProperties(identity, signInIdentity),
                        this::notFoundFunction
                );
        return signInIdentity;
    }

    private void notFoundFunction() {
        throw new UsernameNotFoundException("用户名或密码错误，请重新输入");
    }
}
