package com.lzb.controller;

import com.lzb.pojo.Permission;
import com.lzb.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by luzhibin on 2019/12/17 17:04
 */
@Controller
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping("/permissionList")
    @ResponseBody
    public List<Permission> permissionList(){
        return permissionService.getPermissions();
    }

    /*根据角色查询对应的权限*/
    @RequestMapping("/getPermissionByRid")
    @ResponseBody
    public List<Permission> getPermissionByRid(Long rid){
        System.out.println("rid"+rid);
        return permissionService.getPermissionByRid(rid);
    }
}
