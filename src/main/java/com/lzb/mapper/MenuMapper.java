package com.lzb.mapper;

import com.lzb.pojo.Menu;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    /*保存菜单*/
    void saveMenu(Menu menu);

    Long selectParentId(Long id);

    /*删除操作——打破菜单关系*/
    void updateMenuRel(Long id);

    /*获取树形菜单tree.json的数据*/
    List<Menu> getTreeData();
}