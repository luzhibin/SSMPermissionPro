$(function () {
    /*员式数据列表*/
    $("#dg").datagrid({
        url:"http://localhost:8080/PromissionPro/employeeList",
        columns:[[
            {field:'username',title:'姓名',width:100,align:'center'},
            {field:'inputtime',title:'入职时间',width:100,align:'center'},
            {field:'tel',title:'电话',width:100,align:'center'},
            {field:'email',title:'邮箱',width:100,align:'center'},
            {field:'department',title:'部门',width:100,align:'center'},
            {field:'state',title:'状态',width:100,align:'center',formatter:function (value,row,index) {
                    if (row.state){
                        return "在职";
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
        pagination:true     //导航
    });
});