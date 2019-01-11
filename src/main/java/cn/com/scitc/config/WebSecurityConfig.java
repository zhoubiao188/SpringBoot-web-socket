package cn.com.scitc.config;

import cn.com.scitc.security.AuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    private UserDetailsService  userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler userAuthenticationSuccessHandler;//登录成功处理器
    @Autowired
    private AuthenticationFailureHandler userAuthenctiationFailureHandler;//登录失败处理器

    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider()).eraseCredentials(true);
    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()

                .antMatchers("/css/**","/js/**"
                        ,"/lib/**","/images/**","/Iconfont/**","/sass/**").permitAll()
                .antMatchers("/user/index").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/user/register").permitAll()//注册接口
                .antMatchers("/user/register/personal").permitAll()//注册页面

                .anyRequest().authenticated()//所有的请求需要认证即登陆后才能访问
                .and()
                .formLogin()
                 .loginPage("/user/login")
                .loginProcessingUrl("/login") // 登录交给spring security处理
                .successHandler(userAuthenticationSuccessHandler)
                .failureHandler(userAuthenctiationFailureHandler)

                .usernameParameter("username").passwordParameter("password")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService)
                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout/page")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
                 http
                .cors()
                .and()
                .csrf().disable();
        //以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次
        http.sessionManagement().maximumSessions(1).expiredUrl("/user/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    //添加记住我

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false);
        return tokenRepository;
    }

    //md5加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    //自定义认证
    @Bean
    public AuthProvider authProvider(){
        return  new AuthProvider();
    }

    //认证管理
    @Bean
    public AuthenticationManager authenticationManager() {
        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager =  super.authenticationManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationManager;
    }


}
