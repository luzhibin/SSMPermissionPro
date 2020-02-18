package com.lzb.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzb.mapper.SystemlogMapper;
import com.lzb.pojo.Systemlog;
import com.lzb.service.SystemLogService;
import com.lzb.utils.RequestUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by luzhibin on 2020/2/13 20:25
 */
public class SystemAspect {

    @Autowired
   private SystemlogMapper systemlogMapper;

    public void writeLog(JoinPoint joinPoint) throws JsonProcessingException {
        System.out.println("记录日志");
        //创建系统日志对象
        Systemlog systemlog = new Systemlog();
        //设置时间
        systemlog.setOptime(new Date());
        /*如何获取ip地址？通过request对象:添加拦截器，获取request请求对象*/
        HttpServletRequest request = RequestUtils.getRequest();
        if (request != null){
            //设置ip地址
            String ipAddr = request.getRemoteAddr();
            System.out.println("---------------------------------------------------ip地址："+ipAddr);
            systemlog.setIp(ipAddr);
        }
        /*记录调用的方法：执行方法时会把相应的连接点传进来,获取执行的方法*/
        /*获取目标执行方法的全路径*/
        String name = joinPoint.getTarget().getClass().getName();
        String signature = joinPoint.getSignature().getName();
        String function = name + ":" + signature;
        System.out.println("---------------------------------------------------调用的方法："+function);
        systemlog.setFunction(function);
        /*获取方法的参数*/
        String params = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
        System.out.println("---------------------------------------------------参数:"+params);
        systemlog.setParams(params);
        System.out.println("---------------------------------------------------systemlog："+systemlog);

        //systemlogMapper.insert(systemlog);
    }
}
