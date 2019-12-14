# SSMPermissionPro
## SSM框架之权限系统项目
IDEA连不上Mysql报错  
Server returns invalid timezone. Go to 'Advanced' tab and set 'serverTimezone' property  
原因：服务器返回无效时区，转到“高级”选项卡并手动设置“serverTimezone”属性。
在mysql的命令行窗口输入以下命令即可：
show variables like'%time_zone';
set global time_zone = '+8:00';