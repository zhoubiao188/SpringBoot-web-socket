package cn.com.scitc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class HomeController {


    /**
     * 注册页面
     * @return
     */
    @GetMapping("/register/personal")
    public String registerPage(){


        return "createnewuser";
    }



    /**
     * login
     * @return
     */

    @GetMapping("/login")
    public String login(){
        return "denglu_win";
    }



    /**
     * 聊天室主页
     * @return
     */
    @GetMapping("/home")
    public String homePage(){
        return "Home";
    }

    @GetMapping("/createnewuser")
    public String createnewuserPage(){
        return "createnewuser";
    }

}
