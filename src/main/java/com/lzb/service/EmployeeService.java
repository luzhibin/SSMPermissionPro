package com.lzb.service;

import com.lzb.pojo.Employee;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;

import java.util.List;

/**
 * Created by luzhibin on 2019/11/10 0:23
 */
public interface EmployeeService {
    /*查询员工*/
    PageListRes getEmployee(QueryVo vo);

    //添加员工
    void saveEmployee(Employee employee);

    //更新员工信息
    void updateEmployee(Employee employee);

    //设置员工离职状态
    void updateState(Long id);

    /*根据用户名当中查询有没有当前用户*/
    Employee getEmployeeByUsername(String username);

    /*根据用户ID查询角色表的rnum*/
    List<String> getRolesById(Long id);

    /*根据用户的id查询权限  资源的名称*/
    List<String> getPermissionById(Long id);
}
