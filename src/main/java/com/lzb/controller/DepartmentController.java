package com.lzb.controller;

import com.lzb.pojo.Department;
import com.lzb.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by luzhibin on 2019/12/8 15:50
 */
@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /*查询部门*/
    @RequestMapping("/departList")
    @ResponseBody
    public List<Department> getDepartmentList(){
        return departmentService.getDepartmentList();
    }
}
