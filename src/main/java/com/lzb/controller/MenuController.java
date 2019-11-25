package com.lzb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by luzhibin on 2019/11/9 15:12
 */
@Controller
public class MenuController {

    @RequestMapping("/menu")
    public String employee(){
        return "menu";
    }
}
