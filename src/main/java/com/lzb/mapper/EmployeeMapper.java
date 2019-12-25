package com.lzb.mapper;

import com.lzb.pojo.Employee;
import com.lzb.pojo.QueryVo;
import org.apache.ibatis.annotations.Param;

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

    /*保存员工和角色的关系*/
    void insertEmployeeAndRoleRel(@Param("id") Long id, @Param("rid") Long rid);

    /*打破员工和角色的关系*/
    void deleteRoleRel(Long id);

    /*根据用户名查询是否有该用户*/
    Employee getEmployeeByUsername(String username);
}