package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.dao.MariaDao;
import com.onbrid.test.springboot.springboottest.dao.OnamsDao;
import com.onbrid.test.springboot.springboottest.dao.OracleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class TestController {


    OracleDao oracleDao;

    MariaDao mariaDao;


    @Autowired
    public TestController(OracleDao oracleDao, MariaDao mariaDao) {
        this.oracleDao = oracleDao;
        this.mariaDao = mariaDao;
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("hello");
        log.debug("hello");
        List testList = oracleDao.queryForList("SELECT_COMM_TEST");
        oracleDao.customMethod();
//        log.info("test: {}", testList.toString());
        log.info("Custom test: {}", oracleDao.customMethod());

        try {
            throw new RuntimeException("error msg");
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

        return "hello";
    }


}
