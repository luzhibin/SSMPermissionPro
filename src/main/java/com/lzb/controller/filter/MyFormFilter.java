package com.lzb.controller.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzb.pojo.AjaxRes;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by luzhibin on 2019/12/25 17:22
 */
/*继承一个表单验证的过滤器*/
public class MyFormFilter extends FormAuthenticationFilter {

    /*认证成功时调用*/
    protected boolean onLoginSuccess(AuthenticationToken token,
                                     Subject subject,
                                     ServletRequest request,
                                     ServletResponse response) throws Exception {
        //we handled the success redirect directly, prevent the chain from continuing:
        //issueSuccessRedirect(request, response);
        response.setCharacterEncoding("utf-8");
        //System.out.println("认证成功");
        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setSuccess(true);
        ajaxRes.setMsg("登录成功");
        /*把对象转成JSON格式字符串*/
        String jsonString = new ObjectMapper().writeValueAsString(ajaxRes);
        //System.out.println("json格式的字符串--------------------------"+jsonString);
        response.getWriter().print(jsonString);

        return false;
    }

    /*认证失败时调用*/
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e,
                                     ServletRequest request,
                                     ServletResponse response) {
        System.out.println("认证失败");
        AjaxRes ajaxRes = new AjaxRes();
        ajaxRes.setSuccess(false);
        if(e!=null){
            /*获取异常的名称*/
            String name = e.getClass().getName();
            //判断异常的类型
            if (name.equals(UnknownAccountException.class.getName())){
                /*没有账号*/
                ajaxRes.setMsg("用户名错误或用户名不存在");
            }else if (name.equals(IncorrectCredentialsException.class.getName())){
                /*密码错误*/
                ajaxRes.setMsg("密码错误");
            }else{
                /*未知异常*/
                ajaxRes.setMsg("未知错误");
            }
        }
        try {
            /*把对象转成json格式字符串*/
            String jsonString = new ObjectMapper().writeValueAsString(ajaxRes);
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(jsonString);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        /*响应给浏览器*/
        return false;
    }

}
