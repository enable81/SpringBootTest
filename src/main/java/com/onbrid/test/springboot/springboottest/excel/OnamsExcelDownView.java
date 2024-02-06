package com.onbrid.test.springboot.springboottest.excel;

import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * <a href="https://hermeslog.tistory.com/655">[Spring] 개발환경 구축 - 대용량 Excel 다운로드</a>
 */
@Slf4j
@Component
public class OnamsExcelDownView extends AbstractView {

    private final ExcelService excelService;

    public OnamsExcelDownView(ExcelService excelService) {
        this.excelService = excelService;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        OnBridOnamsData onBridOnamsData = (OnBridOnamsData) model.get(OnBridProperties.PARAM.EXCEL_MODEL_MAP);
        log.debug("onBridOnamsData: {}", onBridOnamsData.getParamMap());
        // ExcelFileName
        String FILE_NM = String.valueOf(onBridOnamsData.getParamMap().get(OnBridProperties.EXCEL.EXCEL_FILE_NAME));
        if (FILE_NM.trim().isEmpty()) {
            FILE_NM = "OnAMS_Excel";
        }
        FILE_NM += ".xlsx";

        String userAgent = request.getHeader("User-Agent");
        boolean ie = userAgent.indexOf("MSIE") > -1;
        String fileName;
        if(ie) {
            fileName = URLEncoder.encode(FILE_NM, "utf-8");
        }
        else {
            fileName = new String(FILE_NM.getBytes("utf-8"), "iso-8859-1");
        }
        
        onBridOnamsData.getParamMap().put(OnBridProperties.EXCEL.EXCEL_FILE_NAME, fileName);

        // .xlsx 파일은 XML 파일이 포함된 압축 파일이기 때문에 POI에서 "Zip Bomb" 관련한 취약점이 발생할 가능성이 있다고 한다.
        // 최소 압축비율을 지정한다.
        ZipSecureFile.setMinInflateRatio(0);

        // Excel File Make
        SXSSFWorkbook workbook = excelService.commonExcelFile(onBridOnamsData.getParamMap());

        response.setContentType("application/msexcel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", " ") + ";");

        OutputStream out = response.getOutputStream();
        try {
            workbook.write(out);
            workbook.close();
            workbook.dispose();
        }
        catch(Exception e) {
            //파일 다운로드 대화창 뜬 후 사용자가 취소를 누르면 ClientAbortException 발생한다.
            if( ! e.getClass().getSimpleName().equals("ClientAbortException")) {
                e.printStackTrace();
            }
        }
        finally {
            if (workbook != null) {
                try {
                    workbook.close();
                    workbook.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    workbook.dispose();
                }
            }
            if(out != null) {
                try {
                    out.close();
                }
                catch(IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

    }



}
