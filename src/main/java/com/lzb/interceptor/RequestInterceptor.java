package com.lzb.interceptor;

import com.lzb.utils.RequestUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by luzhibin on 2020/2/13 20:42
 */
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("来到了拦截器");
        RequestUtils.setRequest(request);
        return true;
    }


}
