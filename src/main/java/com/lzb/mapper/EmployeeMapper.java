package com.lzb.mapper;

import com.lzb.pojo.Employee;
import com.lzb.pojo.QueryVo;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    //左连接查询所有员工及对应的部门
    List<Employee> selectAll(QueryVo vo);

    int updateByPrimaryKey(Employee record);

    //设置员工离职状态
    int updateState(Long id);
}