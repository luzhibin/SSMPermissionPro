package com.lzb.service.impl;

import com.lzb.mapper.PermissionMapper;
import com.lzb.pojo.Permission;
import com.lzb.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by luzhibin on 2019/12/17 17:08
 */
@Transactional
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionMapper.selectAll();
    }

    /**
     * 根据角色rid查询对应的权限
     * @param rid
     * @return
     */
    @Override
    public List<Permission> getPermissionByRid(Long rid) {
       return permissionMapper.selectPermissionByRid(rid);
    }
}
