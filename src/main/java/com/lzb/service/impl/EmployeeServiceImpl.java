package com.lzb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzb.mapper.EmployeeMapper;
import com.lzb.pojo.Employee;
import com.lzb.pojo.PageListRes;
import com.lzb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luzhibin on 2019/11/10 0:26
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public PageListRes getEmployee() {
        System.out.println("来到了业务层");
        /*分页查询*/
        Page<Employee> page = PageHelper.startPage(1, 5);
        List<Employee> employees = employeeMapper.selectAll();
        /*封装成pageList*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }
}
