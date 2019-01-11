package cn.com.scitc.web.controller;

import cn.com.scitc.base.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    /**
     * 没有进行身份认证时候 返回这里的json
     * @return
     */
    @RequestMapping("/login_page")
    @ResponseBody
    public ApiResponse loginPage() {
        return ApiResponse.ofMessage(500,"您还没有登录!");
    }


    @PostMapping("/show")
    @ResponseBody
    public ApiResponse show(){
        return ApiResponse.ofSuccess("成功授权成功,认证成功!");
    }


}
