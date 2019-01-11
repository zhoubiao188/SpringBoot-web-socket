package cn.com.scitc.security;

import cn.com.scitc.entity.User;
import cn.com.scitc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collection;

/**
 * 自定义登录认证
 */
public class AuthProvider  implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取表单登录的用户名
        String username = authentication.getName();
        //获取表单登录的密码
        String InputPassword = (String) authentication.getCredentials();

        User user =  userRepository.findByUsername(username);

        if (user == null){
            logger.info("登录失败");
            throw  new AuthenticationCredentialsNotFoundException("用户名:" + username + "不存在!");
        }

        if (!passwordEncoder.matches(InputPassword,user.getPassword())){
            logger.info("登录失败");
            throw  new BadCredentialsException("密码不正确!");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(user, InputPassword, authorities);
    }



    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }


}
