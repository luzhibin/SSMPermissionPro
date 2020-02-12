package com.lzb.service;

import com.lzb.pojo.AjaxRes;
import com.lzb.pojo.Menu;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;

import java.util.List;

/**
 * Created by luzhibin on 2020/1/26 21:42
 */
public interface MenuService {
    PageListRes getMenuList(QueryVo queryVo);

    /*查询所有菜单*/
    List<Menu> parentList();

    /*保存菜单*/
    void saveMenu(Menu menu);

    AjaxRes updateMenu(Menu menu);

    /*删除菜单*/
    AjaxRes deleteMenu(Long id);

    List<Menu> getTreeData();
}
