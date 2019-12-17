package com.lzb.controller;

import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;
import com.lzb.pojo.Role;
import com.lzb.service.RoleService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
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
    public PageListRes getRoles(QueryVo vo){
        System.out.println(vo);
        return roleService.getRoles(vo);
    }

    @RequestMapping("saveRole")
    @ResponseBody
    public void saveRole(Role role){
        System.out.println(role);
    }
}
