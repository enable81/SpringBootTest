package com.onbrid.test.springboot.springboottest.controller;


import com.onbrid.test.springboot.springboottest.excel.ExcelService;
import com.onbrid.test.springboot.springboottest.view.OnamsExcelDownView;
import com.onbrid.test.springboot.springboottest.exception.OnBridException;
import com.onbrid.test.springboot.springboottest.interceptor.JsonRequestDataReader;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import com.onbrid.test.springboot.springboottest.service.excute.OnAMSServiceInvoker;
import jakarta.servlet.http.HttpServletRequest;
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
            log.debug(onBridOnamsData.toString());

            onBridOnamsData.setRequest(request);

            //DispatcherServlet 이 만든 context 이외에 application root context 도 있을 경우엔 root context 를 가져온다.
            WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
            //service bean 가져오기.
            Object bean = wac.getBean(serviceBeanName);

            //서비스 호출
            result = OnAMSServiceInvoker.invoke(bean, methodName, onBridOnamsData);
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


    // @CrossOrigin(origins = "http://localhost:8080") 일지적인 크로스 도메인 허용
    @PostMapping(path = "/fileService/downloadExcel")
    public Object downloadExcel(HttpServletRequest request, ModelMap modelMap) {
        JsonRequestDataReader requestDataReader = new JsonRequestDataReader(request);
        OnBridOnamsData onBridOnamsData = requestDataReader.getOnBridOnamsData();

        modelMap.put(OnBridProperties.PARAM.EXCEL_MODEL_MAP, onBridOnamsData);

        return new OnamsExcelDownView(excelService);
    }



    @PostMapping(path = "/fileService/downloadFile")
    public Object downloadFile(HttpServletRequest request) {
        JsonRequestDataReader requestDataReader = new JsonRequestDataReader(request);
        OnBridOnamsData onBridOnamsData = requestDataReader.getOnBridOnamsData();


        return onBridOnamsData;
    }
}
