package cn.sicnu;

import cn.hutool.crypto.digest.DigestUtil;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainTest {
    public static void main(String[] args) {
        val passwordEncoder = new PasswordEncoder() {
            /**
             * 加密
             * @param rawPassword 原始密码
             * @return
             */
            @Override
            public String encode(CharSequence rawPassword) {
                return DigestUtil.bcrypt(rawPassword.toString());
            }

            /**
             * 校验密码
             * @param rawPassword       原始密码
             * @param encodedPassword   加密密码
             * @return
             */
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return DigestUtil.bcryptCheck(rawPassword.toString(), encodedPassword);
            }
        };

        System.out.println(passwordEncoder.encode("420130zxc"));
        System.out.println(passwordEncoder.encode("420130zxc"));
        System.out.println(passwordEncoder.encode("420130zxc").equals(passwordEncoder.encode("420130zxc")));
        System.out.println(passwordEncoder.matches("420130zxc", passwordEncoder.encode("420130zxc")));
    }
}
