package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.excel.OnamsExcelDownView;
import com.onbrid.test.springboot.springboottest.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    // final로 생성된 변수는 롬복이(@RequiredArgsConstructor) 알아서 생성자를 만들어준다.
    // 따라서 스프링에 생성자 주입방식으로 인젝션된다. @Autowired 해결 
    final TestService testService;

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
    @GetMapping("/testExcel")
    public String testExcel() {



        return "testExcel";
    }

}
