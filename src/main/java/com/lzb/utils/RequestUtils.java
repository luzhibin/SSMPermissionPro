package com.lzb.utils;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by luzhibin on 2020/2/13 20:49
 */
public class RequestUtils {

    /*本地线程变量*/
    public static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal();

    public static HttpServletRequest getRequest(){
        return requestThreadLocal.get();
    }

    public static void setRequest(HttpServletRequest request){
        requestThreadLocal.set(request);
    }
}
