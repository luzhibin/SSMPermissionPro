package com.lzb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzb.pojo.*;
import com.lzb.service.EmployeeService;
import com.lzb.service.RoleService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import sun.util.resources.ga.LocaleNames_ga;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.jar.JarEntry;

/**
 * Created by luzhibin on 2019/11/9 15:09
 */
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, RoleService roleService) {
        this.employeeService = employeeService;
        this.roleService = roleService;
    }
    private final RoleService roleService;

    @RequestMapping("/employeeList")
    @ResponseBody
    public PageListRes employeeList(QueryVo vo){
        /*调用业务层查询员工*/
        PageListRes pageListRes = employeeService.getEmployee(vo);
        return pageListRes;
    }

    /**
     *     /*当我们在控制器方法写了 @RequiresPermissions,Shiro在访问时,
     *     就会判断有没有该权限,如果没有,就不会执行对应方法*/
    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String employee(){
        return "employee";
    }

    /*employe.jsp的下拉列表*/
    @RequestMapping("/roleList")
    @ResponseBody
    public List<Role> roleList(){
        return roleService.roleList();
    }

    /*添加员工操作*/
    @RequestMapping("/saveEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee){
        System.out.println("----------------------------------------------");
        System.out.println(employee);
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employee.setState(true);
            employeeService.saveEmployee(employee);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("保存失败");
        }
        return ajaxRes;
    }

    /*接收更新员工请求*/
    @RequestMapping("/updateEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
        public AjaxRes updateEmployee(Employee employee){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateEmployee(employee);
            ajaxRes.setMsg("更新信息成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新信息失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*接收离职(删除)操作请求*/
    @RequestMapping("/updateState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateState(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateState(id);
            ajaxRes.setMsg("更新信息成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新信息失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    /*捕获无权限的异常，跳转到无权限的页面*/
    @ExceptionHandler(AuthorizationException.class)
    public void handleShiroException(HandlerMethod method, HttpServletResponse response) throws Exception {  /*handlerMethod:发生异常的方法*/
        /*判断当前的请求是不是JSON请求(通过ResponseBody来判断是否是ajax请求 json数据)
        如果是，则返回json给客户端 让浏览器做跳转*/
        /*获取方法上的注解*/
        ResponseBody methodAnnotation = method.getMethodAnnotation(ResponseBody.class);
        if (methodAnnotation != null){
            /*有responseBody注解，说明是Ajax请求*/
            AjaxRes ajaxRes = new AjaxRes();
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("无权限操作");
            /*转成json字符串*/
            String value = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(value);
        }else{
            //重定向
            response.sendRedirect("nopermission.jsp");
        }
    }
}
