package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.excel.ExcelService;
import com.onbrid.test.springboot.springboottest.excel.OnamsExcelDownView;
import com.onbrid.test.springboot.springboottest.interceptor.JsonRequestDataReader;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import com.onbrid.test.springboot.springboottest.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Test-Controller", description = "API 테스트 컨트롤러 입니다.")
@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    // final로 생성된 변수는 롬복이(@RequiredArgsConstructor) 알아서 생성자를 만들어준다.
    // 따라서 스프링에 생성자 주입방식으로 인젝션된다. @Autowired 해결 
    final TestService testService;

    final ExcelService excelService;

    @GetMapping("/test")
    public String test() {
        // TRACE > DEBUG > INFO > WARN > ERROR
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");

        return "log test";
    }

    /**
     * 엑셀 파일 다운로드 테스트
     * @param request
     * @param response
     * @param modelMap
     * @return
     */
    @Operation(summary = "게시물 등록", description = "제목(title)과 내용(content)을 이용하여 게시물을 신규 등록합니다.")
    @PostMapping("/testExcel")
    public Object testExcel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        OnBridOnamsData excelParamData = new OnBridOnamsData();

        Map paramMap = new HashMap();
        paramMap.put("excelFileName", "TestExce한글_123!@#$");
        excelParamData.setParamMap(paramMap);

        List<Map> excelCols = new ArrayList<>();
        Map col = new HashMap();
        col.put("ASSETNO", "재물번호");
        excelCols.add(col);
        col = new HashMap();
        col.put("ASSETNAME", "재물명");
        excelCols.add(col);
        col = new HashMap();
        col.put("MODELNAME", "모델명");
        excelCols.add(col);
        col = new HashMap();
        col.put("SPEC", "규격");
        excelCols.add(col);
        col = new HashMap();
        col.put("REQ_NAME_EXCEL", "신청현황");
        excelCols.add(col);
        col = new HashMap();
        col.put("ASSETSTATENAME", "처리상태");
        excelCols.add(col);
        col = new HashMap();
        col.put("SERIALNUM", "제조번호");
        excelCols.add(col);
        col = new HashMap();
        col.put("USESTATENAME", "재물상태");
        excelCols.add(col);
        col = new HashMap();
        col.put("RECORDDATE", "취득일자");
        excelCols.add(col);
        col = new HashMap();
        col.put("ACCDATE", "회계전표일자");
        excelCols.add(col);
        col = new HashMap();
        col.put("DEPREGUBNNAME", "감가상각여부");
        excelCols.add(col);
        col = new HashMap();
        col.put("AMOUNT", "금액");
        excelCols.add(col);
        col = new HashMap();
        col.put("DEPTNAME", "부서명");
        excelCols.add(col);
        col = new HashMap();
        col.put("BUILDINGNAME", "건물명");
        excelCols.add(col);
        col = new HashMap();
        col.put("PLACENAME", "장소명");
        excelCols.add(col);
        col = new HashMap();
        col.put("EMPNAME", "신청자");
        excelCols.add(col);
        col = new HashMap();
        col.put("USEEMPNAME", "사용자명");
        excelCols.add(col);
        col = new HashMap();
        col.put("MAKECORP", "제조회사");
        excelCols.add(col);
        col = new HashMap();
        col.put("ACCGUBNNAME", "회계단위");
        excelCols.add(col);
        excelParamData.setExcelColumns(excelCols);


        modelMap.put(OnBridProperties.PARAM.EXCEL_MODEL_MAP, excelParamData);

        return new OnamsExcelDownView(excelService);

    }

}
