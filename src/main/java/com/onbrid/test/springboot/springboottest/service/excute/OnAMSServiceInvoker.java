package com.onbrid.test.springboot.springboottest.service.excute;

import com.onbrid.test.springboot.springboottest.exception.OnBridException;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
public class OnAMSServiceInvoker {


    public static Object invoke(Object bean, String methodName, OnBridOnamsData onamsData) {

        Method method;

        try {

            method = bean.getClass().getMethod("execute", String.class, Object.class);
            return method.invoke(bean, methodName, onamsData);

        }
        catch (Exception e) {
            throw new OnBridException(e);
        }

    }

}
