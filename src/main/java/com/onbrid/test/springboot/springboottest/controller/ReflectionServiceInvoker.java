package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.model.ReqData;

import java.lang.reflect.Method;

public class ReflectionServiceInvoker {


    public static Object invoke(Object bean, String methodName, ReqData vcData) {

        Method method = null;

        try {

            method = bean.getClass().getMethod("execute", String.class, Object.class);
            return method.invoke(bean, methodName, vcData);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
