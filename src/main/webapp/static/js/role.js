$(function () {
    $("#role_dg").datagrid({
        url:"/getRoles",
        columns:[[
            {field:'rnum',title:'角色编号',width:100,align:'center'},
            {field:'rname',title:'角色名称',width:100,align:'center'}
        ]],

        fit:true,   //占满整个页面
        fitColumns:true,    //自动伸缩
        rownumbers:true,    //展示行号
        pagination:true,     //如果为true，则在数据表格控件底部显示分页工具栏。
        singleSelect:true,  //只能同时选择一行
        striped:true,       //斑马线效果
        toolbar:"#role_toolbar"
    });

    /*对话框*/
    $("#role_dialog").dialog({
        width:600,
        height:600,
        buttons:[{
            text:'保存',
            handler:function () {
                /*点击保存时提交表单*/
                $("#myform").form("submit",{
                    url:'/saveRole',//只传了rnum和rname
                    /*需要添加一个onSubmit，通过param参数把权限字段也传给后端*/
                    onSubmit:function(param){//传递额外参数-已选择的权限
                        
                       /*获取已经选择的权限*/
                        var allRows = $("#role_data2").datagrid("getRows");
                        /*遍历每一个权限*/
                        for (var i = 0;i<= allRows.length;i++){
                            /*取出每一个权限*/
                            var row = allRows[i];
                            /*封装到集合permission中*/
                            param["permissions["+i+"].pid"] = row.pid;
                        }
                    },
                    success:function (data) {

                    }
                })
            }
        },{
            text:'关闭',
            handler:function () {
                
            }
        }]
    });

    /*监听添加角色事件的点击*/
    $("#add").click(function () {

    });

    /*权限列表*/
    $("#role_data1").datagrid({
        title:"所有权限",
        width:250,
        height:400,
        fitColumns:true,     //自动伸缩
        singleSelect:true,  //只能同时选择一行
        url:'/permissionList',
        columns:[[
            {field:'pname',title:'权限名称',width:100,align:'center'}
        ]],
        onClickRow:function (rowIndex,rowData) {//点击一行时进行回调
            /*在添加前要判断右边的列表中是否已经存在该权限*/
            /*取出所有的已选权限*/
            var allRows = $("#role_data2").datagrid("getRows");
            //遍历每一个进行判断
            for (var i = 0;i<allRows.length;i++){
                //取出每一行
                var row = allRows[i];
                if (rowData.pid == row.pid){//左侧的pid等于右侧遍历出来的pid，说明该权限已经存在
                    /*让已经存在的权限成为选中状态*/
                    //获取当前权限的当前角标
                    $("#role_data2").datagrid("getRowIndex",row);
                    //让该行成为选中状态
                    $("#role_data2").datagrid("selectRow",index);
                    return;
                }
            }
            /*把当前选中的行，添加到已选权限的列表中*/
            $("#role_data2").datagrid("appendRow",rowData);
        }
    });
    /*选中权限列表*/
    $("#role_data2").datagrid({
        title:"已选权限",
        width:250,
        height:400,
        fitColumns:true,     //自动伸缩
        singleSelect:true,  //只能同时选择一行
        columns:[[
            {field:'pname',title:'权限名称',width:100,align:'center'}
        ]],
        onClickRow:function (rowIndex,rowData) {
        //删除当前选中的行
            $("#role_data2").datagrid("deleteRow",rowIndex);
        }
    })
});