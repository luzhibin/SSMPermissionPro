package com.lzb.service;

import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;

/**
 * Created by luzhibin on 2019/12/16 13:24
 */
public interface RoleService {

    /*查询角色列表*/
    public PageListRes getRoles(QueryVo vo);
}
