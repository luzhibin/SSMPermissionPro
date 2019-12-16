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
        toolbar:"#tb",
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
});