# SSMPermissionPro
## SSM框架之权限系统项目
IDEA连不上Mysql报错  
Server returns invalid timezone. Go to 'Advanced' tab and set 'serverTimezone' property  
原因：服务器返回无效时区，转到“高级”选项卡并手动设置“serverTimezone”属性。
在mysql的命令行窗口输入以下命令即可：
show variables like'%time_zone';
set global time_zone = '+8:00';
2020.02.14  
完成的任务：系统日志  
1.建立日志表和对应mapper  
2.创建日志切面  
3.添加切面，配置AOP  
4.添加拦截器，记录当前请求的操作时间、ip地址    
  ① 创建本地线程变量  
  ② 创建拦截器把当前请求写入到本地线程变量  
  ③ 配置拦截器拦截所有请求  
  ④ 在切面中获取ip  
5.拦截器获取当前执行的方法及参数  

2020.02.15  
完成的任务：  
Excel的导入导出  
1.使用Apache的poi操作Excel  
2.界面搭建：在工具栏加入导入导出按钮  
3.下载功能：把datagrid的数据以Excel的文件格式下载下来  
   (1) 从数据库中取列表数据  
   (2) 创建Excel 写到Excel中  
        ① 设置表头  
        ② 遍历所有员工写入到每一行  
        ③ 入职日期(Date类型)为空时的处理  
   (3) 响应给浏览器  
4.上传功能：用户需要下载Excel模板，按规范填好数据才能进行上传  
   (1) 上传界面的搭建  
        ① 下载模板  
（未完待续......）  
  
2020.02.16  
接02.15  
4.上传功能：用户需要下载Excel模板，按规范填好数据才能进行上传  
   (1) 上传界面的搭建  
        ① 下载模板  
        ② 上传Excel处理  
        ③ 在MVC配置文件中配置文件上传解析器  
至此，基于SSM框架和Shiro安全框架的权限管理系统完成。  
