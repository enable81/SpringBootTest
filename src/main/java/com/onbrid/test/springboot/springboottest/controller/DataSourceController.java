package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.dao.MariaDao;
import com.onbrid.test.springboot.springboottest.dao.OnamsDao;
import com.onbrid.test.springboot.springboottest.dao.OracleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DataSourceController {
//
//    OnamsDao onamsDao;
//
//    OracleDao oracleDao;
//
//    MariaDao mariaDao;
//
//
//    @Autowired
//    public DataSourceController(OnamsDao onamsDao, OracleDao oracleDao, MariaDao mariaDao) {
//        this.onamsDao = onamsDao;
//        this.oracleDao = oracleDao;
//        this.mariaDao = mariaDao;
//
//    }
//
//
//    @GetMapping("/onams")
//    public String onamsData() {
//
//        onamsDao.queryForList("SELECT_COMM_TEST");
//
//        return "onams-datasource";
//    }
//
//    @GetMapping("/oracle")
//    public String oracleData() {
//
//        oracleDao.queryForList("SELECT_COMM_TEST");
//        oracleDao.customMethod();
//
//        return "oracle-datasource";
//    }
//
//    @GetMapping("/maria")
//    public String mariaData() {
//
//        mariaDao.queryForList("SELECT_COMM_TEST");
//        mariaDao.customMethod();
//
//        return "maria-datasource";
//    }


}
