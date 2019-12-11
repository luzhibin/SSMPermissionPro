package com.lzb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzb.mapper.EmployeeMapper;
import com.lzb.pojo.Employee;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;
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
    public PageListRes getEmployee(QueryVo vo) {
        /*分页查询,后面做高级查询时把值传进来*/
        Page<Employee> page = PageHelper.startPage(vo.getPage(), vo.getRows());
        List<Employee> employees = employeeMapper.selectAll();
        /*封装成pageList*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(employees);
        return pageListRes;
    }

    @Override   //添加员工
    public void saveEmployee(Employee employee){
        employeeMapper.insert(employee);
    }

    @Override   //更新员工信息
    public void updateEmployee(Employee employee){
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Override //修改员工离职状态
    public void updateState(Long id){
        employeeMapper.updateState(id);
    }
}
