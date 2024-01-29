package com.onbrid.test.springboot.springboottest.controller;


import com.onbrid.test.springboot.springboottest.exception.OnBridException;
import com.onbrid.test.springboot.springboottest.model.ReqData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class ReflectionController {


    @RequestMapping(path = "/{serviceBeanName}/{methodName}", method = {RequestMethod.GET, RequestMethod.POST})
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
            //ex.printStackTrace();
            Throwable cause = ex.getCause();
            Throwable mainCause = cause == null ? ex : null;
            while (cause != null) {
                log.debug("while (cause != null)");
                mainCause = cause;
                cause = cause.getCause();
                //mainCause.printStackTrace();
                //cause.printStackTrace();
            }

            // TODO: root cause stack 을 DB에 저장.


            Exception throwEx = null;
            if (mainCause instanceof OnBridException) {
                throwEx = (OnBridException) mainCause;
            }
            else {
                throwEx = new OnBridException(mainCause);
            }

            throw throwEx;
        }


        return result;
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
