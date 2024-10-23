package com.onbrid.test.springboot.springboottest.interceptor;

import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

public class OnamsModelAndViewResolver implements HandlerMethodReturnValueHandler {


    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.getParameterType().getSimpleName().equals("OnBridOnamsData");
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();

//        if (returnValue instanceof OnBridOnamsData) {
//
//            String viewName = "OnamsDefaultView";
//            OnBridOnamsData onamsData = (OnBridOnamsData) returnValue;
//            if (onamsData != null && "Y".equals(onamsData.get)) {
//
//            }
//
//
//        }
    }
}
