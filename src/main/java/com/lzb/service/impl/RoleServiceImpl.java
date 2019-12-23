package com.lzb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzb.mapper.RoleMapper;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.Permission;
import com.lzb.pojo.QueryVo;
import com.lzb.pojo.Role;
import com.lzb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luzhibin on 2019/12/16 13:24
 */
@Service
@Transactional  //开启事务
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * 查询所有角色
     * @param vo
     */
    @Override
    public PageListRes getRoles(QueryVo vo) {
        /*设置分页*/
        Page<Object> page = PageHelper.startPage(vo.getPage(),vo.getRows());

        List<Role> roles = roleMapper.selectAll();
        /*封装成pageList，做一个返回*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(roles);
        return pageListRes;
    }

    @Override
    public void saveRole(Role role) {
        /*1.保存角色*/
        roleMapper.insert(role);
        /*2.保存角色关联的权限*/
        //遍历权限
        for (Permission permission:role.getPermissions()){
            roleMapper.insertRoleAndPermissionRel(role.getRid(),permission.getPid());
        }
    }

    public void updateRole(Role role){
        /*打破角色和权限的关系*/
        roleMapper.deletePermissionRel(role.getRid());
        /*更新角色*/
        roleMapper.updateByPrimaryKey(role);
        /*重新建立角色与权限的关系*/
        //遍历权限
        for (Permission permission:role.getPermissions()){
            roleMapper.insertRoleAndPermissionRel(role.getRid(),permission.getPid());
        }
    }

    /*删除角色的业务*/
    @Override
    public void deleteRole(Long rid) {
        /*删除关联的权限*/
        roleMapper.deletePermissionRel(rid);
        /*删除对应的角色*/
        roleMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public List<Role> roleList() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Long> getRoleByEid(Long id) {
        return roleMapper.getRoleById(id);
    }
}
