package com.lzb.controller;

import com.lzb.pojo.AjaxRes;
import com.lzb.pojo.Employee;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;
import com.lzb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.jar.JarEntry;

/**
 * Created by luzhibin on 2019/11/9 15:09
 */
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/employeeList")
    @ResponseBody
    public PageListRes employeeList(QueryVo vo){
        /*调用业务层查询员工*/
        PageListRes pageListRes = employeeService.getEmployee(vo);
        return pageListRes;
    }

    @RequestMapping("/employee")
    public String employee(){
        return "employee";
    }

    @RequestMapping("/saveEmployee")
    @ResponseBody
    public AjaxRes saveEmployee(Employee employee){
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

    @RequestMapping("/updateEmployee")
    @ResponseBody
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

    @RequestMapping("/updateState")
    @ResponseBody
    public AjaxRes updateState(Long id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            employeeService.updateState(id);
            ajaxRes.setMsg("更新信息成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            System.out.println(e);
            ajaxRes.setMsg("更新信息失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
