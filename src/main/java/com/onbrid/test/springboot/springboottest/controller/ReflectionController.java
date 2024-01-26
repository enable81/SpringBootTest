package com.onbrid.test.springboot.springboottest.controller;


import com.onbrid.test.springboot.springboottest.model.ReqData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class ReflectionController {


    @RequestMapping(path = "/{serviceBeanName}/{methodName}")
    @ResponseBody
    public Object doAction(HttpServletRequest request, HttpServletResponse response,
                           ReqData reqData,
                           @PathVariable("serviceBeanName") String serviceBeanName,
                           @PathVariable("methodName") String methodName) throws Exception {
        Object result = null;

        try {

            //DispatcherServlet 이 만든 context 이외에 application root context 도 있을 경우엔 root context 를 가져온다.
            WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());

            //service bean 가져오기.
            Object bean = wac.getBean(serviceBeanName);

            //서비스 호출
            result = ReflectionServiceInvoker.invoke(bean, methodName, reqData);

        }
        catch (Throwable ex) {
            ex.printStackTrace();
        }


        return getResultMap((ReqData) result);
    }


    private Map getResultMap(ReqData valueChainData) {

//        Map allDataMap = valueChainData.getAllData();
//
//        if(null != valueChainData.getVariableMap()) {
//            Map temp = valueChainData.getVariableMap();
//            Set<String> keyList = temp.keySet();
//
//            for(Object key : keyList) {
//                allDataMap.put(key, temp.get(key));
//            }
//        }


        Map allDataMap = new HashMap();
        return allDataMap;
    }
}
