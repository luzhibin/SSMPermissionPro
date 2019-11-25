package com.lzb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luzhibin on 2019/11/9 15:15
 */
@Controller
public class RoleController {

    @RequestMapping("/role")
    public String role(){
        return "role";
    }
}
