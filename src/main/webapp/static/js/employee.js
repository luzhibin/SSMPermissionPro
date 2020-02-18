$(function () {
    /*员工数据列表*/
    $("#dg").datagrid({
        pageSize: 20,//每页显示的记录条数，默认为10
        pageList: [20,40,60,80],//可以设置每页记录条数的列表
        url:"/employeeList",
        columns:[[
            {field:'username',title:'姓名',width:100,align:'center'},
            {field:'inputtime',title:'入职时间',width:100,align:'center'},
            {field:'tel',title:'电话',width:100,align:'center'},
            {field:'email',title:'邮箱',width:100,align:'center'},
            {field:'department',title:'部门',width:100,align:'center',formatter:function (value,row,index) {
                if (value){
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
        toolbar:"#employee_toolbar",
        /*在用户点击一行的时候触发，参数包括：
          rowIndex：点击的行的索引值，该索引值从0开始。
          rowData：对应于点击行的记录。*/
        onClickRow:function (rowIndex,rowData) {
            //判断当前行是否是离职状态
            if (rowData.state){ //state为true
                //未离职，离职按钮启用
                $("#delete").linkbutton("enable");
            } else {
                //已离职，把离职按钮禁用
                $("#delete").linkbutton("disable");
            }
        }
    });

    /*对话框*/
    $("#employee_dialog").dialog({
        closed:true,    //是否隐藏对话框
        width:300,
        height:400,
        resizable:true,  //定义对话框是否可调整尺寸
        closeOnEscape:true, //按ESC关闭对话框
        shadow:true,
        buttons:[{
            text:'保存',
            handler:function(){
                /*判断当前是添加还是编辑*/
                var id = $("[name='id']").val();
                var url;
                if (id){
                    /*如果能获取到ID值  说明是编辑操作*/
                    url = "updateEmployee";
                }else{/*否则是添加操作*/
                    url = "saveEmployee";
                }
                /*提交表单*/
                $("#employeeForm").form("submit",{
                    url:url,
                    onSubmit:function(param){
                        /*获取选中的角色*/
                        var values = $("#role").combobox("getValues");
                        /*对参数进行遍历*/
                        for (var i = 0; i < values.length; i++){
                            var rid = values[i];
                            param["roles["+i+"].rid"] = rid;
                        }
                    },
                    success:function (data) {
                        data = $.parseJSON(data);   //把data转成JSON
                        if (data.success){
                            $.messager.alert("提示",data.msg,"info");
                            /*提示成功后关闭对话框*/
                            $("#employee_dialog").dialog("close");
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
                $("#employee_dialog").dialog("close");
            }
        }]
    });

    /*监听添加按钮的点击事件*/
    $("#add").click(function () {
        //$("#employee_dialog").dialog("setTittle","添加员工");
        //$("#employee_dialog").dialog("open");
        $("#password").show();
        $("#employeeForm").form("clear");
        $("#admin").combobox('setValue','false');
        /*添加密码验证*/
        $("[name='password']").validatebox({required:true});
        $("#employee_dialog").dialog({title:"添加员工"}).dialog('open');
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
        /*取消密码验证*/
        $("[name='password']").validatebox({required:false});
        $("#password").hide();      //隐藏密码字段
        /*弹出对话框并设置标题*/
        $("#employee_dialog").dialog({title:"编辑员工"}).dialog('open');
        /*回显部门*/
        rowData["department.id"] = rowData["department"].id;
        /*回显管理员*/
        rowData["admin"]=rowData["admin"]+"";
        /*回显角色——根据当前用户的id，查出对应的角色*/
        $.get("/getRoleByEid?id="+rowData.id,function (data) {
           /*设置下拉列表数据的回显*/
           $("#role").combobox("setValues",data);
        });
        /*做选中数据的回显*/
        $("#employeeForm").form("load",rowData);
    });

    /*部门选择 combobox-下拉列表*/
    $("#department").combobox({
       width:'auto',
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
        width:'auto',
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
            $("#admin").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

    /*选择角色下拉列表*/
    $("#role").combobox({
        width:'auto',
        panelHeight:'auto', //自动高度
        editable:false,     //不允许编辑该下拉框
        url:"roleList",
        valueField:'rid',     //把rid发送给服务端
        textField:'rname',     //展示给前端的数据
        multiple:true,          //定义是否支持多选
        onLoadSuccess:function () { //在加载远程数据成功的时候触发:显示placeholder里的内容
            $("#role").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

    /*监听离职按钮的点击事件*/
    $("#delete").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#dg").datagrid("getSelected");
        console.log(rowData);
        if (!rowData){
            $.messager.alert("提示","请选择一行数据进行编辑");
            return;
        }
        $.messager.confirm("提示","是否确认离职操作",function (res) {
            if(res){
                //做离职操作
                $.get("/updateState?id="+rowData.id,function (data) {
                    if (data.success){
                        $.messager.alert("提示",data.msg,"info");
                        /*并且重新加载数据表格*/
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("提示",data.msg,"error");
                    }
                })
            }
        });
    });

    /*监听搜索按钮的点击事件*/
    $("#search").click(function () {
        /*获取搜索的内容*/
        var keyword = $("[name='keyword']").val();
        //重新加载列表  把参数keyword传过去
        $("#dg").datagrid("load",{keyword:keyword});
    });

    /*监听刷新点击*/
    $("#reload").click(function () {
        /*清空搜索内容*/
        var keyword = $("[name='keyword']").val();
        /*重新加载数据*/
        $("#dg").datagrid("load",{});
    });

    $("#excelOut").click(function () {
        window.open('/downloadExcel');
    });

    $("#excelUpload").dialog({
        width:260,
        height:180,
        title:"导入Excel",
        buttons:[{
            text:'保存',
            handler:function(){
                $("#uploadForm").form("submit",{
                    url:"/uploadExcelFile",
                    success:function (data) {
                        data = $.parseJSON(data);   //把data转成JSON
                        if (data.success){
                            $.messager.alert("提示",data.msg,"info");
                            /*提示成功后关闭对话框*/
                            $("#excelUpload").dialog("close");
                            /*并且重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("提示",data.msg,"error");
                            $("#dg").datagrid("reload");
                        }
                    }
                })
            }
        },{
            text:'关闭',
            handler:function(){
                $("#excelUpload").dialog("close");
            }
        }],
        closed:true
    });

    $("#excelIn").click(function () {
        alert(" 请先下载模板，按照模板填写员工信息 \n 在职状态默认为：在职 \n 是否为管理员默认为：否 \n 所属部门默认为空，请导入Excel后在“员工管理→编辑”中修改");
        $("#excelUpload").dialog("open");
    });

    /*下载Excel模板*/
    $("#downloadTml").click(function () {
        window.open("/downloadExcelTpl")
    })
});