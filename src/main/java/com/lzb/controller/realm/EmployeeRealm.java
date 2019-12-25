package com.lzb.controller.realm;

import com.lzb.pojo.Employee;
import com.lzb.service.EmployeeService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

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
        if (employee==null){
            return null;
        }

        /*交由认证器进行认证*/
        /*参数：主体，密码，盐值，当前realm名称*/
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                employee.getUsername(),
                employee.getPassword(),
                ByteSource.Util.bytes("saltValue"),
                this.getName());
        return null;
    }

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}
