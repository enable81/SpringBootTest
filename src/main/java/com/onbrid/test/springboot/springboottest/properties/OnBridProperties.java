package com.onbrid.test.springboot.springboottest.properties;

public class OnBridProperties {

    public static final int ERROR_CODE_FAIL_VALUE = -99999;  //에러  발생 시 에러 코드  값  셋팅

    public final class PARAM {
        public static final String LIST = "list";
        public static final String REQUEST_READER = "REQUEST_READER";
        public static final String EXCEL_MODEL_MAP = "EXCEL_MODEL_MAP";
    }

    public final class EXCEL {
        /**
         * Excel File Name 은 확장자 뺀 파일이름만<br>
         * 예: 재물목록
         */
        public static final String EXCEL_COLS = "EXCELCOLUMNS";
        public static final String EXCEL_FILE_NAME = "excelFileName";
    }

}
