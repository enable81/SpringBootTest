package com.onbrid.test.springboot.springboottest.interceptor;

import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class OnamsHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        JsonRequestDataReader requestDataReader = new JsonRequestDataReader();
        requestDataReader.init(request);
        request.setAttribute(OnBridProperties.PARAM.REQUEST_READER, requestDataReader);

        return true;
    }





}
