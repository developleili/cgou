package xyz.lilei.cgou.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginRedirect
 * @Author lilei
 * @Date 12/07/2020 20:10
 * @Version 1.0
 **/
@Controller
@RequestMapping("/oauth")
public class LoginRedirectController {

    /**
     * 跳转到登陆页面
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
