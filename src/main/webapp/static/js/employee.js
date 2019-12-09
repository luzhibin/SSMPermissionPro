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
        pagination:true,     //导航
        toolbar:"#tb"
    });

    /*对话框*/
    $("#dialog").dialog({
       /* closed:true,*/
        width:400,
        height:400,
        resizable:true,  //定义对话框是否可调整尺寸
        buttons:[{
            text:'保存',
            handler:function(){
                /*提交表单*/
                $("#employeeForm").form("submit",{
                    url:"save_employee",
                    success:function (data) {
                        console.log(data)
                    }
                });
            }
        },{
            text:'关闭',
            handler:function(){

            }
        }]
    });

    /*监听添加按钮的点击事件*/
    $("#add").click(function () {
        $("#dialog").dialog('open');
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
    $("#state").combobox({
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