package com.lzb.service;

import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;
import com.lzb.pojo.Role;

import java.util.List;

/**
 * Created by luzhibin on 2019/12/16 13:24
 */
public interface RoleService {

    /*查询角色列表*/
    PageListRes getRoles(QueryVo vo);

    /*保存角色*/
    void saveRole(Role role);

    /*更新角色*/
    void updateRole(Role role);

    /*删除角色*/
    void deleteRole(Long rid);

    /*employe.jsp的下拉列表*/
    List<Role> roleList();

    /*根据用户id查询对应的角色*/
    List<Long> getRoleByEid(Long id);
}
