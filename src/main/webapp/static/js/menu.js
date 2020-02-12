$(function () {

    /*数据表格*/
    $("#menu_datagrid").datagrid({
        url:"/menuList",
        columns:[[
            {field:'text',title:'名称',width:1,align:'center'},
            {field:'url',title:"跳转地址",width:1,align:'center'},
            {field:'parent',title:"父菜单",width:1,align:'center',formatter:function(value,row,index){
                    return value?value.text:'';
                }
            }
        ]],
        fit:true,
        rownumbers:true,
        singleSelect:true,
        striped:true,
        pagination:true,
        fitColumns:true,
        toolbar:'#menu_toolbar'
    });

    /*
     * 初始化新增/编辑对话框
     */
    $("#menu_dialog").dialog({
        width:300,
        height:300,
        closed:true,
        buttons:'#menu_dialog_bt'
    });

    /*加载选择父菜单*/
    $("#parentMenu").combobox({
        width:150,
        panelHeight:"auto",  //自动高度
        url:"parentList",
        valueField:'id',     //发送给服务端
        textField:'text',     //展示给前端的数据
        editable:false,
        onLoadSuccess:function () { //在加载远程数据成功的时候触发:显示placeholder里的内容
            $("#parentMenu").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

    /*添加菜单*/
    $("#menu_add").click(function () {
        $("#menu_form").form("clear");
        $("#menu_dialog").dialog("setTitle","保存菜单");/*设置标题*/
        $("#menu_dialog").dialog("open");
    });

    /*保存菜单*/
    $("#save").click(function () {

        /*判断当前是保存还是编辑*/
        var id = $("[name='id']").val();
        var url;
        if (id){
            var parent_id = $("[name='parent.id']").val();
            if (id == parent_id){
                //alert("不能设置自己为父菜单");
                $.messager.alert("提示","不能设置自己为父菜单","info");
                return;
            }
            /*如果能获取到ID值  说明是编辑操作*/
            url = "updateMenu";
        }else{/*否则是添加操作*/
            url = "saveMenu";
        }

        /*提交表单*/
        $("#menu_form").form("submit",{
            url:url,
            success:function (data) {
                data = $.parseJSON(data);   //把data转成JSON
                if (data.success){
                    $.messager.alert("提示",data.msg,"info");
                    /*提示成功后关闭对话框*/
                    $("#menu_dialog").dialog("close");
                    /*重新加载父菜单*/
                    $("#parentMenu").combobox("reload");
                    /*并且重新加载数据表格*/
                    $("#menu_datagrid").datagrid("reload");
                } else {
                    $.messager.alert("提示",data.msg,"error");
                }
            }
        });
    });

    /*编辑菜单*/
    $("#menu_edit").click(function () {

        $("#menu_form").form("clear");
        /*获取当前选中的行*/
        var  rowData = $("#menu_datagrid").datagrid("getSelected");
        if (!rowData){
            $.messager.alert("提示","请选择一条数据进行编辑");
            return;
        }
        /*父菜单回显*/
        if(rowData.parent){
            rowData["parent.id"] = rowData.parent.id;
        }else{
            /*回显的placeHolder*/
            $("#parentMenu").each(function(i){
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput){
                    $(targetInput).attr("placeholder",$(this).attr("placeholder"));
                }
            });
        }
        /*弹出对话框*/
        $("#menu_dialog").dialog("setTitle","编辑菜单");/*设置标题*/
        $("#menu_dialog").dialog("open");
        /*选中数据的回显*/
        $("#menu_form").form("load",rowData);
    });

    /*对话框dialog的取消按钮*/
    $("#cancel").click(function () {
       $("#menu_dialog").dialog("close");
    });

    $("#menu_del").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#menu_datagrid").datagrid("getSelected");
        if (!rowData){
            $.messager.alert("提示","请选择一行数据进行删除");
            return;
        }
        $.messager.confirm("提示","是否确认离职操作",function (res) {
            if(res){
                //做删除操作
                $.get("/deleteMenu?id="+rowData.id,function (data) {
                    if (data.success){
                        $.messager.alert("提示",data.msg,"info");
                        /*重新加载下拉列表的数据*/
                        $("#parentMenu").combobox("reload");
                        /*并且重新加载数据表格*/
                        $("#menu_datagrid").datagrid("reload");
                    } else {
                        $.messager.alert("提示",data.msg,"error");
                    }
                })
            }
        });

    });
});