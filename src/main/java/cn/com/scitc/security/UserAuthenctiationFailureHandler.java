package cn.com.scitc.security;

import cn.com.scitc.base.ApiResponse;
import cn.com.scitc.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Component("userAuthenctiationFailureHandler")
public class UserAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token," +
                " Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        logger.info("登录失败!" + new Date());

        ApiResponse apiResponse = null;
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            apiResponse = ApiResponse.ofMessage(500,"密码输入错误!");
        }

        else {
            apiResponse = ApiResponse.ofMessage(401,"用户不存在!");
        }

        response.setStatus(401);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(apiResponse));
        out.flush();
        out.close();
    }

}
