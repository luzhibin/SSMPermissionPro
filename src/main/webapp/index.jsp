<%--
  Created by IntelliJ IDEA.
  User: 12558
  Date: 2019/11/9
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限管理系统</title>
    <%@include file="/static/common/common.jsp" %>
</head>

<body class="easyui-layout">
<%--头部--%>
<div data-options="region:'north'" style="height:100px; background: #ec4e00; padding: 20px 20px;margin: 0 auto;">
    <img src="static/images/main_logo.png" alt="">
</div>
<%--底部--%>
<div data-options="region:'south'" style="height:50px; border-bottom: 3px solid #ec4e00;margin:0 auto;">
    <p align="center" style="font-size: 14px">撩课学院</p>
</div>
<%--左侧菜单--%>
<div data-options="region:'west',split:true" style="width:300px;margin:0 auto;">

    <div id="aa" class="easyui-accordion" data-options="fit:true">
        <div title="菜单" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">
            <!--tree-->
            <ul id="tree"></ul>
        </div>
        <div title="公告" data-options="iconCls:'icon-reload'" style="padding:10px;">

        </div>
    </div>
</div>
<%--右侧主区域--%>
<div data-options="region:'center'" style="background:#eee; margin:0 auto;">
    <!--标签-->
    <div id="tabs" style="overflow: hidden" >

    </div>
</div>
</body>
</html>
