package com.lzb.controller;

import com.lzb.pojo.QueryVo;
import com.lzb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by luzhibin on 2019/11/9 15:15
 */
@Controller
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping("/role")
    public String role(){
        return "role";
    }

    /*接收角色列表请求*/
    @RequestMapping("/getRoles")
    @ResponseBody
    public void getRoles(QueryVo vo){
        System.out.println(vo);
        roleService.getRoles(vo);
    }
}
