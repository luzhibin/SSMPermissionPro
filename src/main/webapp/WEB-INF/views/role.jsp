<%--
  Created by IntelliJ IDEA.
  User: 12558
  Date: 2019/11/9
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Role.jsp</title>
    <%@include file="/static/common/common.jsp" %>
    <style>
        #role_dialog #myform .panel-header{
            height: 25px;
        }
        #role_dialog #myform .panel-title{
            color: black;
            margin-top:-5px;
        }
    </style>
</head>
<body>

<%--工具栏--%>
<div id="role_toolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="role_add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="role_edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="role_remove">删除</a>
</div>

<%--数据表格 --%>
<div id="role_dg"></div>

<%--对话框--%>
<div id="role_dialog">
    <form id="myform">
        <table align="center" style="border-spacing: 20px 30px">
            <input type="hidden" name="rid">
            <tr align="center">
                <td>角色编号: <input type="text" name="rnum" class="easyui-validatebox" ></td>
                <td>角色名称: <input type="text" name="rname" class="easyui-validatebox" ></td>
            </tr>
            <tr>
                <td><div id="role_data1"></div></td>
                <td><div id="role_data2"></div></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
