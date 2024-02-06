package com.onbrid.test.springboot.springboottest.excel;

import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import java.util.Map;

@Slf4j
@Component
public class OnamsFileDownView extends AbstractView {

    private final ExcelService excelService;

    public OnamsFileDownView(ExcelService excelService) {
        this.excelService = excelService;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        OnBridOnamsData onBridOnamsData = (OnBridOnamsData) model.get(OnBridProperties.EXCEL_MODEL_MAP_PARAM);
        log.debug("onBridOnamsData: {}", onBridOnamsData.getParamMap());
        log.debug("onBridOnamsData: {}", onBridOnamsData.getParamMap().get("REQUEST_URI"));

    }



}
