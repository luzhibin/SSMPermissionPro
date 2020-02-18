<%--
  Created by IntelliJ IDEA.
  User: 12558
  Date: 2019/11/9
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工主页</title>
    <%@include file="/static/common/common.jsp" %>
</head>
<body>
<div id="excelUpload">
    <form method="post" enctype="multipart/form-data" id="uploadForm">
        <tabel>
            <tr>
                <td><input type="file" name="excel" style="width: 180px; margin-top: 20px; margin-left: 5px;"></td>
                <td><a href="javascript:void(0);" id="downloadTml">下载模板</a></td>
            </tr>
        </tabel>
    </form>
</div>
<%--工具栏--%>
<div id="employee_toolbar">
    <%--shiro标签库--%>
    <%--当shiro发现有添加权限的时候才会加载添加按钮--%>
    <shiro:hasPermission name="employee:add">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    </shiro:hasPermission>

    <shiro:hasPermission name="employee:edit">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    </shiro:hasPermission>

    <shiro:hasPermission name="employee:delete">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">离职</a>
    </shiro:hasPermission>

    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
    <input type="text" name="keyword" style="width: 200px; height: 30px;padding-left: 5px;">
    <a class="easyui-linkbutton" iconCls="icon-search" id="search">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" id="excelIn">Excel导入</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" id="excelOut">Excel导出</a>
</div>
<%--数据表格--%>
<table id="dg"></table>
<%--对话框--%>
<div id="employee_dialog" class="dialog" style="margin: 0 auto">
    <form id="employeeForm">
        <%--添加一个隐藏域 专门用于编辑操作--%>
        <input type="hidden" name="id">
        <table align="center" style="border-spacing: 5px 10px">
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
            <tr id="password">
                <td>密码:</td>
                <td><input type="text" name="password" class="easyui-validatebox"/></td>
            </tr>
            <tr>
                <td>手机:</td>
                <td><input type="text" name="tel" class="easyui-validatebox"/></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" name="email" class="easyui-validatebox"/></td>
            </tr>
            <tr>
                <td>入职日期:</td>
                <td><input type="text" name="inputtime" class="easyui-datebox"/></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td><input id="department" name="department.id" placeholder="请选择部门" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>是否管理员:</td>
                <td><input id="admin" name="admin" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>选择角色:</td>
                <td><input id="role" name="role.rid" placeholder="请选择角色" data-options="required:true"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

