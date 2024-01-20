package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.dao.MariaDao;
import com.onbrid.test.springboot.springboottest.dao.OnBridDao;
import com.onbrid.test.springboot.springboottest.dao.OnamsDao;
import com.onbrid.test.springboot.springboottest.dao.OracleDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DataSourceProfileController {

    OnBridDao onBridDao;


    @Autowired
    public DataSourceProfileController(OnBridDao onBridDao) {
        this.onBridDao = onBridDao;
    }



    @GetMapping("/oracle")
    public String oracleData() {

        onBridDao.queryForList("SELECT_COMM_TEST");
        //onBridDao.customMethod();

        return "oracle-datasource";
    }

    @GetMapping("/maria")
    public String mariaData() {

        onBridDao.queryForList("SELECT_COMM_TEST");
        //onBridDao.customMethod();

        return "maria-datasource";
    }


}
