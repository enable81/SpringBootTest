package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.dao.MariaDao;
import com.onbrid.test.springboot.springboottest.dao.OracleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        List testList = mariaDao.queryForList("SELECT_COMM_TEST");
        log.info("test: {}", testList.toString());
        log.info("Custom test: {}", oracleDao.customMethod());



        return "hello";
    }


}
