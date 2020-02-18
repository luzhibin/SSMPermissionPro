package com.lzb.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lzb.mapper.MenuMapper;
import com.lzb.pojo.*;
import com.lzb.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by luzhibin on 2020/1/26 21:43
 */
@Transactional
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public PageListRes getMenuList(QueryVo queryVo) {

        /*分页使用PageHelper插件*/
        Page<Object> page = PageHelper.startPage(queryVo.getPage(), queryVo.getRows());
        List<Menu> menus = menuMapper.selectAll();
        /*把查询结果封装成pageList*/
        PageListRes pageListRes = new PageListRes();
        pageListRes.setTotal(page.getTotal());
        pageListRes.setRows(menus);
        return pageListRes;
    }

    @Override
    public List<Menu> parentList() {
        return menuMapper.selectAll();
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.saveMenu(menu);
    }

    @Override
    public AjaxRes updateMenu(Menu menu) {
        AjaxRes ajaxRes = new AjaxRes();
        /*判断 选择的父菜单 是否是自己的子菜单*/
        /*不能设置为自己的子菜单*/

        /*选取出当前选择父菜单id*/
        Long id = menu.getParent().getId();
        System.out.println("打印测试看取出的是哪个ID--------------------------------------:" + id);
        /*查询该id对应的menu*/
        Long parent_id = menuMapper.selectParentId(id);
        System.out.println("打印测试取出的ID--------------------------------------:" + parent_id);

        if (menu.getId() == parent_id) {
            ajaxRes.setMsg("不能设置自己的子菜单为父菜单");
            ajaxRes.setSuccess(false);
            return ajaxRes;
        }

        try {
            /*调用service层，更新菜单*/
            menuMapper.updateByPrimaryKey(menu);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("更新失败");
        }
        return ajaxRes;
    }

    /*删除菜单*/
    @Override
    public AjaxRes deleteMenu(Long id) {

        /*1.打破菜单关系*/
        menuMapper.updateMenuRel(id);
        /*2.删除记录*/
        menuMapper.deleteByPrimaryKey(id);
        AjaxRes ajaxRes = new AjaxRes();
        try {
            /*调用service层，更新菜单*/
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        } catch (Exception e) {
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("删除失败");
        }
        return ajaxRes;
    }

    /*获取树形菜单*/
    @Override
    public List<Menu> getTreeData() {
        List<Menu> treeData = menuMapper.getTreeData();
        /*判断当前用户有没有访问菜单权限*/
        /*如果没有就从当前集合中移除*/

        /*获取用户，判断用户是否是管理员，是管理就不需要做判断*/
        Subject subject = SecurityUtils.getSubject();
        /*当前用户*/
        Employee employee = (Employee) subject.getPrincipal();
        if (!employee.getAdmin()) {
            /*做权限校验*/
            checkPermission(treeData);
        }
        return treeData;
    }

    private void checkPermission(List<Menu> menus) {
        /*获取主体*/
        Subject subject = SecurityUtils.getSubject();
        //遍历所有菜单及子菜单（使用集合迭代器）
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            if (menu.getPermission() != null) {
                /*判断当前menu是否有权限对象*/
                String presource = menu.getPermission().getPresource();
                if (!subject.isPermitted(presource)) {
                    //没有权限对象，当前遍历的菜单需要从集合中移除
                    iterator.remove();
                    continue;
                }
            }
            /*判断是否有子菜单*/
            if (menu.getChildren().size() > 0) {
                /*有子菜单，做权限校验*/
                checkPermission(menu.getChildren());
            }
        }
    }
}
