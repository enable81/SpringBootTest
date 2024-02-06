package com.onbrid.test.springboot.springboottest.excel;

import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExcelService {

    public SXSSFWorkbook commonExcelFile(Map paramMap) {

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        workbook.setCompressTempFiles(true);

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(String.valueOf(paramMap.get(OnBridProperties.EXCEL.EXCEL_FILE_NAME)));
        sheet.setRandomAccessWindowSize(3000);    // 메모리 행 3000개로 제한, 초과 시 Disk로 Flush

        // 1. Title 생성
        setTitle(sheet, model);

        sheet.createRow(sheet.getLastRowNum() + 1);         // Last Row

        // 2. 헤더 생성
        setHeader(sheet, model);

        // 3. Data 생성
        setData(sheet, model);

        return workbook;


    }

}
