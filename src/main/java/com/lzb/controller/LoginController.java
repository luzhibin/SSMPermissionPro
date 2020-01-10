package com.lzb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by luzhibin on 2019/12/23 16:59
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        System.out.println("来到了loginController----------------------------");
        return "redirect:login.jsp";
    }
}
