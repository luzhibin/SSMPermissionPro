package com.lzb.mapper;

import com.lzb.pojo.Employee;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    //设置员工离职状态
    int updateState(Long id);
}