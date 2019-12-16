package com.lzb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzb.mapper.RoleMapper;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;
import com.lzb.pojo.Role;
import com.lzb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luzhibin on 2019/12/16 13:24
 */
@Service
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
}
