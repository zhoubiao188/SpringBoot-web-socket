package cn.com.scitc.web.controller;
import cn.com.scitc.base.ApiResponse;
import cn.com.scitc.entity.Message;
import cn.com.scitc.entity.User;
import cn.com.scitc.repository.UserRepository;
import cn.com.scitc.service.MessageSerice;
import cn.com.scitc.service.UserService;
import cn.com.scitc.web.form.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 用来给浏览器发信息
     */
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageSerice messageSerice;

    @PostMapping("/register")
    @ResponseBody
    public ApiResponse registerPage(@Valid UserForm userForm, BindingResult bindingResult, @RequestParam(value = "username",required = false) String username,
                                    @RequestParam(value = "password",required = false) String password,
                                    @RequestParam(value = "nickname",required = false) String nickname) {

        if (userService.findByUsernameUser(username) != null) {

            return ApiResponse.ofMessage(500, "用户名已被注册:" + username);

        } else if (bindingResult.hasErrors()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST.value(), bindingResult.getAllErrors().get(0).getDefaultMessage(), null);

        } else {

            User user = userService.addUser(username, password, nickname);
            return ApiResponse.ofSuccess(user);
        }

    }

    /**
     * webcoket 消息接口
     * @param principal
     * @param message
     */
    @MessageMapping(value = "/chat")
    @ResponseBody
    public void handleChat(Principal principal, Message message) {

        logger.info("服务器正在转发一条消息--------------------");
        logger.info("消息发送者:" + principal.getName());
        logger.info("消息接受者:" + message.getReceiver());
        logger.info("消息的内容是:" + message.getText());
        message.setSender(principal.getName());
        messageSerice.saveMsg(message);
         if (message.getText() == ""){
             return;
         }
        else if (message.getReceiver().equals("")) {
            logger.info("不填接收者，这是广播");
            simpMessagingTemplate.convertAndSendToUser("", "/notification",
                    "@广播(" + principal.getName() + "): " + message.getText());
        } else {
            simpMessagingTemplate.convertAndSendToUser(message.getReceiver(), "/notification",
                    principal.getName() + ": " + message.getText());
        }
        logger.info("服务器完成该转发-------------------------");

    }
}
