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
<%--工具栏--%>
<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">离职</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
</div>
<%--数据表格--%>
<table id="dg"></table>
<%--对话框--%>
<div id="dialog" class="dialog">
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
        </table>
    </form>
</div>
</body>
</html>

