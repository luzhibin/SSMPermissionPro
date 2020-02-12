package com.lzb.controller;

import com.lzb.pojo.AjaxRes;
import com.lzb.pojo.Menu;
import com.lzb.pojo.PageListRes;
import com.lzb.pojo.QueryVo;
import com.lzb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by luzhibin on 2019/11/9 15:12
 */
@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu")
    public String employee(){
        return "menu";
    }

    @RequestMapping("/menuList")
    @ResponseBody
    public PageListRes menuList(QueryVo queryVo){
        /*调用业务层查询菜单*/
        PageListRes pageListRes = menuService.getMenuList(queryVo);
        return pageListRes;
    }

    /*加载父菜单*/
    @RequestMapping("/parentList")
    @ResponseBody
    public List<Menu> parentList(){
        /*查询所有菜单*/
        return menuService.parentList();
    }

    /*保存菜单*/
    @RequestMapping("/saveMenu")
    @ResponseBody
    public AjaxRes savaMenu(Menu menu){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            /*调用service层，保存菜单*/
            menuService.saveMenu(menu);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setSuccess(false);
            ajaxRes.setMsg("保存失败");
        }
        return ajaxRes;
    }

    /*更新菜单*/
    @RequestMapping("/updateMenu")
    @ResponseBody
    public AjaxRes updateMenu(Menu menu){
        return menuService.updateMenu(menu);
    }

    /*删除菜单*/
    @RequestMapping("/deleteMenu")
    @ResponseBody
    public AjaxRes deleteMenu(Long id){
        return menuService.deleteMenu(id);
    }

    /*获取树形菜单数据 tree.json*/
    @RequestMapping("/getTreeData")
    @ResponseBody
    public List<Menu> getTreeData(){

        return menuService.getTreeData();
    }
 }
