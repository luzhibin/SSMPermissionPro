$(function () {
    /*员式数据列表*/
    $("#dg").datagrid({
        url:"http://localhost:8080/PromissionPro/employeeList",
        columns:[[
            {field:'username',title:'姓名',width:100,align:'center'},
            {field:'inputtime',title:'入职时间',width:100,align:'center'},
            {field:'tel',title:'电话',width:100,align:'center'},
            {field:'email',title:'邮箱',width:100,align:'center'},
            {field:'department',title:'部门',width:100,align:'center',formatter:function (value,row,index) {
                if (value.name){
                    return value.name;
                }
            }},
            {field:'state',title:'状态',width:100,align:'center',formatter:function (value,row,index) {
                    if (row.state){
                        return "<font style='color: green'>在职</font>";
                    }else {
                        return "<font style='color: red;'>离职</font>"
                    }
                }},
            {field:'admin',title:'管理员',width:100,align:'center',formatter:function (value,row,index) {
                    if (row.admin){
                        return "是";
                    }else {
                        return "否"
                    }
                }}
        ]],

        fit:true,   //占满整个页面
        fitColumns:true,    //自动伸缩
        rownumbers:true,    //展示行号
        pagination:true,     //如果为true，则在数据表格控件底部显示分页工具栏。
        singleSelect:true,  //只能同时选择一行
        striped:true,       //斑马线效果
        toolbar:"#tb"
    });

    /*对话框*/
    $("#dialog").dialog({
        closed:true,    //是否隐藏对话框
        width:400,
        height:400,
        resizable:true,  //定义对话框是否可调整尺寸
        closeOnEscape:true, //按ESC关闭对话框
        buttons:[{
            text:'保存',
            handler:function(){
                /*提交表单*/
                $("#employeeForm").form("submit",{
                    url:"save_employee",
                    success:function (data) {
                        data = $.parseJSON(data);   //把data转成JSON
                        if (data.success){
                            $.messager.alert("提示",data.msg,"info");
                            /*提示成功后关闭对话框*/
                            $("#dialog").dialog("close");
                            /*并且重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("提示",data.msg,"error");
                        }
                    }
                });
            }
        },{
            text:'关闭',
            handler:function(){
                $("#dialog").dialog("close");
            }
        }]
    });

    /*监听添加按钮的点击事件*/
    $("#add").click(function () {
        //$("#dialog").dialog("setTittle","添加员工");
        //$("#dialog").dialog("open");
        $("#dialog").dialog({title:"添加员工"}).dialog('open');
        $("#password").show();
        $("#state").show();
        $("#employeeForm").form("clear");
    });

    /*监听编辑按钮点击事件*/
    $("#edit").click(function () {
        $("#employeeForm").form("clear");
        /*获取当前选中的行*/
        var rowData = $("#dg").datagrid("getSelected");
        console.log(rowData);
        if (!rowData){
            $.messager.alert("提示","请选择一行数据进行编辑");
            return;
        }
        $("#password").hide();      //隐藏密码字段
        /*弹出对话框并设置标题*/
        $("#dialog").dialog({title:"编辑员工"}).dialog('open');
        /*回显部门*/
        rowData["department.id"] = rowData["department"].id;
        /*回显管理员*/
        rowData["admin"]=rowData["admin"]+"";
        /*做选中数据的回显*/
        $("#employeeForm").form("load",rowData);
    });

    /*部门选择 combobox-下拉列表*/
    $("#department").combobox({
       width:150,
       panelHeight:"auto",  //自动高度
       url:"departList",
       valueField:'id',     //把部门id发送给服务端
       textField:'name',     //展示给前端的数据
       editable:false,
        onLoadSuccess:function () { //在加载远程数据成功的时候触发:显示placeholder里的内容
            $("#department").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

    /*是否为管理员*/
    $("#admin").combobox({
        width:150,
        panelHeight:'auto', //自动高度
        valueField:'value', //指定发送给服务器的字段
        textField:'label',  //在前段文本框展示的字段
        editable:false,     //不允许编辑该下拉框
        data:[{
            label:'是',
            value:'true'
        },{
            label:'否',
            value:'false'
        }],
        onLoadSuccess:function () { //在加载远程数据成功的时候触发:显示placeholder里的内容
            $("#state").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

});