package com.onbrid.test.springboot.springboottest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onbrid.test.springboot.springboottest.excel.ExcelService;
import com.onbrid.test.springboot.springboottest.excel.OnamsExcelDownView;
import com.onbrid.test.springboot.springboottest.exception.JsonParsingException;
import com.onbrid.test.springboot.springboottest.exception.OnBridException;
import com.onbrid.test.springboot.springboottest.interceptor.JsonRequestDataReader;
import com.onbrid.test.springboot.springboottest.model.FileInfo;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import com.onbrid.test.springboot.springboottest.properties.OnBridProperties;
import com.onbrid.test.springboot.springboottest.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Test-Controller", description = "API 테스트 컨트롤러 입니다.")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test/v1")
@RestController
public class TestController {

    // final로 생성된 변수는 롬복이(@RequiredArgsConstructor) 알아서 생성자를 만들어준다.
    // 따라서 스프링에 생성자 주입방식으로 인젝션된다. @Autowired 해결
    final ExcelService excelService;

    final FileService fileService;



    @PostMapping(path = "/fileService/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploldFile(HttpServletRequest request,
                             @Parameter(description = "File to upload", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
                             @RequestPart(value = "files") List<MultipartFile> multipartFiles,
                             @Parameter(name = "info", example = "{\"univNo\":\"0001\"}") @RequestParam String info) {
        log.debug("info: {}", info);
        ObjectMapper objectMapper = new ObjectMapper();
        FileInfo fileInfo;
        try {
            fileInfo = objectMapper.readValue(info, FileInfo.class);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException("[업로드파일] - 파라미터가 Json String 형식이 아닙니다.", e);
        }
        if (multipartFiles.size() < 1) {
            throw new OnBridException(-999999, "[업로드파일] - 첨부파일이 없습니다.");
        }

        log.debug(fileInfo.toString());

        return null; //fileService.writeFile(request, multipartFiles, fileInfo);
    }

    @PostMapping(path = "/fileService/downloadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object downloadFile(@RequestParam String path, @RequestParam String name) {



        Resource resource = fileService.readFileAsResource(path, name);
        try {
            String filename = URLEncoder.encode(name, "UTF-8");
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + filename + "\";")
//                    .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "")
//                    .body(resource);

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("filename encoding failed : " + name);
        }

        return null; //fileService.writeFile(request, multipartFiles, fileInfo);
    }




    /**
     * 엑셀 파일 다운로드 테스트
     */
    @Operation(summary = "엑셀 다운로드 테스트")
    @PostMapping("/excelDownload")
    public Object excelDownload(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        OnBridOnamsData excelParamData = new OnBridOnamsData();

        Map paramMap = new HashMap();
        paramMap.put(OnBridProperties.PARAM.SERVICE_BEAN_NAME, "testService");
        paramMap.put(OnBridProperties.PARAM.METHOD_NAME, "selectTestBigData");
        paramMap.put(OnBridProperties.EXCEL.EXCEL_FILE_NAME, "TestExce한글_123!@#$");
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



    @GetMapping("/log")
    public String logTest() {
        // TRACE > DEBUG > INFO > WARN > ERROR
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");

        return "log test";
    }
}
