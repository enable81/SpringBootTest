package com.onbrid.test.springboot.springboottest.controller;

import com.onbrid.test.springboot.springboottest.dao.OnamsDao;
import com.onbrid.test.springboot.springboottest.dao.OtherSystemDao;
import com.onbrid.test.springboot.springboottest.model.OnBridOnamsData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class DataSourceController {

    OnamsDao onamsDao;

    OtherSystemDao osDao;

    @Autowired
    public DataSourceController(OnamsDao onamsDao, OtherSystemDao osDao) {
        this.onamsDao = onamsDao;
        this.osDao = osDao;
    }

    @GetMapping("/onams")
    public List<Map> onamsData() {

        onamsDao.queryForList("SELECT_TEST");
        onamsDao.customMethod();
        return onamsDao.customMethod();
    }

    @GetMapping("/douzone")
    public Map oracleData() {
        osDao.queryForList("SELECT_TEST");
        osDao.customMethod();

        return (Map) osDao.customMethod().get(0);
    }

}
