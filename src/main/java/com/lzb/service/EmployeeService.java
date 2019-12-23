package com.lzb.service;

import com.lzb.pojo.Employee;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;

/**
 * Created by luzhibin on 2019/11/10 0:23
 */
public interface EmployeeService {
    /*查询员工*/
    public PageListRes getEmployee(QueryVo vo);

    //添加员工
    void saveEmployee(Employee employee);

    //更新员工信息
    void updateEmployee(Employee employee);

    //设置员工离职状态
    void updateState(Long id);


}
