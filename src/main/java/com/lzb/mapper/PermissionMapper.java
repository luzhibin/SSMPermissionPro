package com.lzb.mapper;

import com.lzb.pojo.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long pid);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);
}