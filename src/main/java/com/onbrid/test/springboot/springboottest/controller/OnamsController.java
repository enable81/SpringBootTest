package com.onbrid.test.springboot.springboottest.controller;


import com.onbrid.test.springboot.springboottest.excel.ExcelService;
import com.onbrid.test.springboot.springboottest.excel.OnamsExcelController;
import com.onbrid.test.springboot.springboottest.excel.OnamsFileDownView;
import com.onbrid.test.springboot.springboottest.exception.OnBridException;
import com.onbrid.test.springboot.springboottest.interceptor.JsonRequestDataReader;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import com.onbrid.test.springboot.springboottest.service.excute.OnAMSServiceInvoker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/api/v1")
public class OnamsController {

    final ExcelService excelService;

    @RequestMapping(path = "/{serviceBeanName}/{methodName}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object doAction(HttpServletRequest request,
//                           HttpServletResponse response,
                           @PathVariable("serviceBeanName") String serviceBeanName,
                           @PathVariable("methodName") String methodName) throws Exception {
        Object result;

        try {
            JsonRequestDataReader requestDataReader = new JsonRequestDataReader(request);
            OnBridOnamsData onBridOnamsData = requestDataReader.getOnBridOnamsData();

            //DispatcherServlet 이 만든 context 이외에 application root context 도 있을 경우엔 root context 를 가져온다.
            WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
            //service bean 가져오기.
            Object bean = wac.getBean(serviceBeanName);

            //서비스 호출
            result = OnAMSServiceInvoker.invoke(bean, methodName, onBridOnamsData);
            result = onBridOnamsData;
        }
        catch (Throwable ex) {
            Throwable cause = ex.getCause();
            Throwable mainCause = cause == null ? ex : null;
            while (cause != null) {
                mainCause = cause;
                cause = cause.getCause();
            }

            // TODO: root cause stack 을 DB에 저장.


            Exception throwEx;
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


    @RequestMapping(path = "/fileService/excelDownload")
    public Object excelDownload(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        JsonRequestDataReader requestDataReader = new JsonRequestDataReader(request);
        OnBridOnamsData excelParamData = requestDataReader.getOnBridOnamsData();
        log.debug("excelDownload: {}", excelParamData);
        modelMap.put(OnBridProperties.EXCEL_MODEL_MAP_PARAM, excelParamData);


        return new OnamsFileDownView(excelService);
    }

}