package com.onbrid.test.springboot.springboottest.excel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class ExcelService {
    private final static String COLUMN_KEY      = "KEY";
    private final static String COLUMN_HEADER   = "HEADER";
    private final static String COLUMN_TYPE    = "TYPE";

    private SXSSFWorkbook workbook;

    /**
     * [구버전]   EXCELCOLUMNS = [{ASSETNO: 재물번호}, {ASSETNAME: 재물명}]<br/>
     * [api/v1] EXCELCOLUMNS = [{KEY: ASSETNO, HEADER: 재물번호, TYPE: NUM(INT)|FLT(FLOAT,DOUBLE, DBL)|STRING(STR)|DATE(DT)}, {KEY: ASSETNAME, HEADER: 재물명, TYPE:STRING}]<br/>
     * @param onBridOnamsData {EXCELCOLUMNS:[], 조회 조건1: "", 조회 조건2: ""  }
     * @return
     * @throws JsonProcessingException
     */
    public SXSSFWorkbook commonExcelFile(OnBridOnamsData onBridOnamsData) {
        log.debug("ExcelService.onBridOnamsData: {}", onBridOnamsData.toString());
        log.debug("ExcelService.onBridOnamsData.ExcelColumns: {}", onBridOnamsData.getExcelColumns());
        // 데이터 조회
        // 데이터가 있을 때만 만든다.??
        List<Map> datas = new ArrayList<>();
        Map item;
        for (int k=0; k<100000; k++) {
            item = new HashMap();
            item.put("ASSETNO", "A-" + k);
            item.put("ASSETNAME", "AN-" + k);
            datas.add(item);
        }


        workbook = new SXSSFWorkbook();
        workbook.setCompressTempFiles(true);

        SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet(String.valueOf(onBridOnamsData.getParamMap().get(OnBridProperties.EXCEL.EXCEL_FILE_NAME)));
        sheet.setRandomAccessWindowSize(3000);    // 메모리 행 3000개로 제한, 초과 시 Disk로 Flush

        List<Map> columns = onBridOnamsData.getExcelColumns();
        if (!columns.get(0).containsKey(COLUMN_KEY)) {
            // excel 컬럼명 설정
            columns = this.setColumnsData(columns);
        }
        // 1. Title 생성
        // setTitle(sheet, model);
        // sheet.createRow(sheet.getLastRowNum() + 1);         // Last Row

        // 2. 헤더 생성
        setHeader(sheet, columns);

        // 3. Data 생성
        setData(sheet, columns, datas);

        return workbook;

    }


    private void setHeader(SXSSFSheet sheet, List<Map> columns) {
        SXSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);         // Last Row

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle = getFontStyle(cellStyle, 13, true);
        cellStyle = getCellStyle(cellStyle, "center");
        cellStyle = getColorStyle(cellStyle, "grey25");

        SXSSFCell cell;
        for (int i = 0; i < columns.size(); i++) {
            cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(new XSSFRichTextString(String.valueOf(columns.get(i).get(COLUMN_HEADER))));
            sheet.setColumnWidth(i, 4000);
        }
    }


    private void setData(SXSSFSheet sheet, List<Map> columns, List<Map> data) {

        CreationHelper helper      = workbook.getCreationHelper();
        CellStyle      numberStyle = getNumberStyle(helper);
        CellStyle      intStyle    = getIntStyle(helper);
        CellStyle      dateStyle   = getDateStyle(helper);
        CellStyle      stringStyle = getStringStyle();

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle = getFontStyle(cellStyle, 10, true);
        cellStyle = getCellStyle(cellStyle, "center");

        SXSSFRow  row;
        SXSSFCell cell = null;
        String type;
        if ( data != null ) {
            for (Map<String, Object> rowData: data ) {
                row = sheet.createRow(sheet.getLastRowNum() + 1);         // Last Row

                for ( int i = 0; i < columns.size(); i++ ) {

                    if (rowData.containsKey(columns.get(i).get(COLUMN_KEY))) {
                        type = String.valueOf(columns.get(i).get(COLUMN_TYPE)).toUpperCase();
                        if (type.equals("NUM") || type.equals("INT")) {
                            cell = row.createCell(i);
                            cell.setCellStyle(intStyle);
                            cell.setCellValue(new XSSFRichTextString((String) rowData.get(columns.get(i).get(COLUMN_KEY))));
                        }
                        else if (type.equals("DT") || type.equals("DATE")) {
                            cell = row.createCell(i);
                            cell.setCellStyle(dateStyle);
                            cell.setCellValue(new XSSFRichTextString((String) rowData.get(columns.get(i).get(COLUMN_KEY))));
                        }
                        else if (type.equals("FLT") || type.equals("FLOAT") || type.equals("DOUBLE") || type.equals("DBL")) {
                            cell = row.createCell(i);
                            cell.setCellStyle(numberStyle);
                            cell.setCellValue(new XSSFRichTextString((String) rowData.get(columns.get(i).get(COLUMN_KEY))));
                        }
                        else if (type.equals("NUL")) {
                            cell = row.createCell(i);
                            cell.setCellStyle(cellStyle);
                            cell.setCellValue(new XSSFRichTextString(""));
                        }
                        else {
                            // STR, String, Etc
                            cell = row.createCell(i);
                            cell.setCellStyle(stringStyle);
                            cell.setCellValue(new XSSFRichTextString((String) rowData.get(columns.get(i).get(COLUMN_KEY))));
                        }
                    }
                }
            }
        }
    }


    private List<Map> setColumnsData(List<Map> paramColums){
        List<Map> columns = new ArrayList<>();
        Map col;
        String key;
        for( int i=0 ; i<paramColums.size() ; i++ ){
            Iterator<String> it = paramColums.get(i).keySet().iterator();
            while( it.hasNext() ){
                key  =   it.next();
                // 단순 순차(로우넘버)는 컬럼으로 안 만든다.
                if( ! key.equalsIgnoreCase("NUM")){
                    col =   new HashMap();
                    col.put(COLUMN_KEY , key);
                    col.put(COLUMN_HEADER, paramColums.get(i).get(key) );
                    col.put(COLUMN_TYPE, null);
                    columns.add(col);
                }
            }// while
        }

        return columns;
    }

    /**
     * 컬럼생성 : 데이터를 기준으로 컬럼 생성
     */
    /*private void setColumnsData(List<Map> columns, List<Map> rows){

    }*/


    public CellStyle getFontStyle( CellStyle cellStyle, int fontSize, boolean boldYn ) {
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) fontSize);
        font.setBold(boldYn);
        cellStyle.setFont(font);

        return cellStyle;
    }

    public CellStyle getColorStyle( CellStyle cellStyle, String color ) {
        if ( color.equals("grey25") ) {
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        } else if ( color.equals("yellow") ) {
            cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        }

        cellStyle.setFillPattern(FillPatternType.FINE_DOTS);

        return cellStyle;
    }

    private CellStyle getCellStyle( CellStyle cellStyle, String align ) {
        if ( align.equals("center") ) {
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        } else if ( align.equals("left") ) {
            cellStyle.setAlignment(HorizontalAlignment.LEFT);
        } else if ( align.equals("right") ) {
            cellStyle.setAlignment(HorizontalAlignment.RIGHT);
        } else {
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
        }

        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        return cellStyle;
    }

    private CellStyle getStringStyle() {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);

        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        return cellStyle;
    }

    private CellStyle getIntStyle( CreationHelper helper ) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(helper.createDataFormat().getFormat("#,##0"));
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);

        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        return cellStyle;
    }

    private CellStyle getNumberStyle( CreationHelper helper ) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(helper.createDataFormat().getFormat("#,##0.0#"));
        cellStyle.setAlignment(HorizontalAlignment.RIGHT);

        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        return cellStyle;
    }

    private CellStyle getDateStyle( CreationHelper helper ) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(helper.createDataFormat().getFormat("yyyy-mm-dd"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        return cellStyle;
    }

}
