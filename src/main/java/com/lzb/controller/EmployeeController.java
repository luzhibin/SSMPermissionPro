package com.lzb.controller;

import com.lzb.pojo.PageListRes;
import com.lzb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by luzhibin on 2019/11/9 15:09
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employeeList")
    @ResponseBody
    public PageListRes employeeList(){
        /*调用业务层查询员工*/
        PageListRes pageListRes = employeeService.getEmployee();
        return pageListRes;
    }

    @RequestMapping("/employee")
    public String employee(){
        return "employee";
    }
}
