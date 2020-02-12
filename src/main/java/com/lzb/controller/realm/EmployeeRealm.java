package com.lzb.controller.realm;

import com.lzb.pojo.Employee;
import com.lzb.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luzhibin on 2019/12/25 16:38
 */
public class EmployeeRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeService employeeService;

    /*身份验证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("doGetAuthorizationInfo======身份验证");
        String username = (String) token.getPrincipal();   //通过token获取身份信息
        System.out.println(username);
        /*到数据库中查询是否有当前用户*/
        Employee employee = employeeService.getEmployeeByUsername(username);
        System.out.println(employee);
        if (employee==null){
            return null;
        }

        /*交由认证器进行认证*/
        /*参数：主体，密码，盐值，当前realm名称*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                employee,
                employee.getPassword(),
                ByteSource.Util.bytes(employee.getUsername()),
                this.getName());
        return info;
    }

    /**授权
     * web doGetAuthorizationInfo 什么时候调用
     * 1.发现访问路径对应的方法上有授权的注解  就会调用doGetAuthorizationInfo
     * 2.页面中有授权的标签 也会调用
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权调用--------------------------------");
        /*获取用户的身份信息*/
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        /*根据当前的用户查询角色和对应的权限*/
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();

        /*判断当前用户是否是管理员，如果是 则拥有所有权限*/
        if(employee.getAdmin()){
            permissions.add("*:*");
        }else{
            /*根据员工id查询角色表的rnum*/
            roles = employeeService.getRolesById(employee.getId());
            System.out.println("roles:============================"+roles);
            /*根据用户的id查询权限 资源的名称*/
            permissions = employeeService.getPermissionById(employee.getId());
            System.out.println("Permission:============================"+permissions);
        }

        /*给予授权信息*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

}
