$(function () {
    /*角色数据列表*/
    $("#role_dg").datagrid({
        url:"getRoles",
        columns:[[
            {field:'rnum',title:'角色编号',width:100,align:'center'},
            {field:'rname',title:'角色名称',width:100,align:'center'}
        ]],
        fit:true,           //占满整个页面
        fitColumns:true,       //自动伸缩
        rownumbers:true,        //展示行号
        pagination:true,        //如果为true，则在数据表格空间底部显示分页工具栏
        singleSelect:true,      //只能同时选择一行
        striped:true,           //斑马线效果
        toolbar:"#role_toolbar"
    });

    /*添加/编辑对话框*/
    $("#role_dialog").dialog({
        width:600,
        height:650,
        buttons:[{
            text:'保存',
            handler:function(){

                /*判断当前是保存操作还是编辑操作*/
                var rid =  $("[name='rid']").val();
                var url;
                /*如果rid有值*/
                if(rid){
                    /*编辑操作*/
                    url="updateRole";
                }else {
                    /*添加操作*/
                    url="saveRole";
                }

                /*点击保存时提交表单*/
                $("#myform").form("submit",{
                    url:url,
                    onSubmit:function(param){  /*传递额外参数  已选择的权限*/

                        /*获取右侧已经选择的权限-pid*/
                        var allRows =  $("#role_data2").datagrid("getRows");//获取右侧的内容，取出里面所有的数据封装到集合中
                        /*遍历出每一个权限——即pid*/
                        for(var i = 0; i< allRows.length; i++){
                            /*取出每一个权限 */
                            var row =  allRows[i];
                            /*给它封装到集合permissions中*/
                            param["permissions["+i+"].pid"] = row.pid;
                        }

                    },
                    success:function (data) {
                        data = $.parseJSON(data);
                        if (data.success){
                            $.messager.alert("提示",data.msg,"info");
                            /*关闭对话框 */
                            $("#role_dialog").dialog("close");
                            /*重新加载数据表格*/
                            $("#role_dg").datagrid("reload");
                        } else {
                            $.messager.alert("提示",data.msg,"error");
                        }
                    }
                });

            }
        },{
            text:'关闭',
            handler:function(){
                $("#role_dialog").dialog("close");
            }
        }],
        closed:true
    });

    /*监听添加角色事件的点击*/
    $("#role_add").click(function () {
        /*添加完数据后清空表单*/
        $("#myform").form("clear");
        /*添加完数据后清空对话框*/
        $("#role_data2").datagrid("loadData",{rows:[]});
        /*设置标题*/
        $("#role_dialog").dialog("setTitle","添加角色");
        /*打开对话框 */
        $("#role_dialog").dialog("open");
    });

    /*权限列表*/
    $("#role_data1").datagrid({
        title:"所有权限",
        width:250,
        height:400,
        fitColumns:true,         //自动伸缩
        singleSelect:true,      //只能同时选择一行
        url:'permissionList',
        columns:[[
            {field:'pname',title:'权限名称',width:100,align:'center'},
        ]],
        onClickRow:function (rowIndex,rowData) {/*点击一行时进行回调*/

            /*在添加前要判断右边的列表中是否已经存在该权限*/
            /*取出所有的已选权限*/
            var allRows = $("#role_data2").datagrid("getRows");
            /*遍历每一个进行判断*/
            for(var i = 0; i<allRows.length; i++){
                /*取出每一行*/
                var row = allRows[i];
                if(rowData.pid == row.pid){/*左侧的pid等于右侧遍历出来的pid，说明该权限已经存在*/
                    /*让已经存在权限成为选中的状态*/
                    /*获取已经成为选中状态当前角标*/
                    var index = $("#role_data2").datagrid("getRowIndex",row);
                    /*让该行成为选中状态*/
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
        singleSelect:true,
        fitColumns:true,
        columns:[[
            {field:'pname',title:'权限名称',width:100,align:'center'},
        ]],
        onClickRow:function (rowIndex,rowData) {
            /*删除当中选中的一行*/
            $("#role_data2").datagrid("deleteRow",rowIndex);
        }
    });

    /*监听编辑点击*/
    $("#role_edit").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#role_dg").datagrid("getSelected");
        console.log(rowData);
        if(!rowData){
            $.messager.alert("提示","选择一行数据进行编辑");
            return;
        }

        /*加载当前角色下的权限*/
        var options =  $("#role_data2").datagrid("options");
        options.url = "getPermissionByRid?rid="+rowData.rid;
        /*重新加载数据*/
        $("#role_data2").datagrid("load");

        /*选中数据的回示*/
        $("#myform").form("load",rowData);
        /*设置标题*/
        $("#role_dialog").dialog("setTitle","编辑角色");
        /*打开对话框 */
        $("#role_dialog").dialog("open");
    });

    /*监听删除点击*/
    $("#role_remove").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#role_dg").datagrid("getSelected");
        console.log(rowData);
        if(!rowData){
            $.messager.alert("提示","选择一行数据进行删除");
            return;
        }
        $.messager.confirm("提示","是否确定删除",function (res) {
            if (res){
                /*删除*/
                $.get("deleteRole?rid="+rowData.rid,function (data) {
                    if (data.success){
                        $.messager.alert("提示",data.msg,"info");
                        /*重新加载数据表格*/
                        $("#role_dg").datagrid("reload");
                    } else {
                        $.messager.alert("提示",data.msg,"error");
                    }
                })
            }
        });
    });

});