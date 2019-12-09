package com.lzb.controller;

import com.lzb.pojo.Employee;
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

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

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

    @RequestMapping("/save_employee")
    @ResponseBody
    public void saveEmployee(Employee employee){
        System.out.println(employee);
        employee.setState(true);
        employeeService.saveEmployee(employee);

        System.out.println("提交表单成功");
    }
}
