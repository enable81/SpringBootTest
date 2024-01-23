package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.dao.OnamsDao;
import com.onbrid.test.springboot.springboottest.dao.OtherSystemDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DataSourceController {

    OnamsDao onamsDao;

    OtherSystemDao osDao;

    @Autowired
    public DataSourceController(OnamsDao onamsDao, OtherSystemDao osDao) {
        this.onamsDao = onamsDao;
        this.osDao = osDao;

    }


    @GetMapping("/onams")
    public String onamsData() {

        onamsDao.queryForList("SELECT_COMM_TEST");
        onamsDao.customMethod();

        return "onams-datasource";
    }

    @GetMapping("/douzone")
    public String oracleData() {

        osDao.queryForList("SELECT_COMM_TEST");
        osDao.customMethod();

        return "oracle-datasource";
    }



}
