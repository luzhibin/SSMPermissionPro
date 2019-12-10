package com.lzb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzb.mapper.EmployeeMapper;
import com.lzb.pojo.Employee;
import com.lzb.pojo.PageListRes;
import com.lzb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luzhibin on 2019/11/10 0:26
 */
@Service("employeeService")
@Transactional  //事务
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public PageListRes getEmployee() {
        /*分页查询,后面做高级查询时把值传进来*/
        Page<Employee> page = PageHelper.startPage(1, 15);
        List<Employee> employees = employeeMapper.selectAll();
        /*封装成pageList*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }

    @Override
    public void saveEmployee(Employee employee){
        employeeMapper.insert(employee);
    }
}
