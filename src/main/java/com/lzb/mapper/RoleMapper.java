package com.lzb.mapper;

import com.lzb.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(Role record);

    Role selectByPrimaryKey(Long rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    //保存角色和权限的关系
    int insertRoleAndPermissionRel(@Param("rid") Long rid, @Param("pid") Long pid);

    /*打破角色与权限之间的关系*/
    void deletePermissionRel(Long rid);

    /*根据用户id查询对应角色*/
    List<Long> getRoleById(Long id);
}