package com.onbrid.test.springboot.springboottest.dao;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@DependsOn(value = {"mariaSqlSession"})
@Slf4j
@Profile(value = {"db-maria"})
public class MariaDao extends OnamsDao {

    @Autowired
    public MariaDao(@Qualifier("mariaSqlSession") SqlSessionTemplate sqlSessionTemplate) {
        super(sqlSessionTemplate);
        log.debug("Maria Dao init...");
    }


    @Override
    public List queryForList(String sqlId) {
        log.debug("Maria Dao.queryForList");
        return sqlSessionTemplate.selectList(NAMESPACE + sqlId);
    }

    public List customMethod() {
        // 어떤 작업...
        log.debug("MariaDao Custom Method");

        Map param = new HashMap();
        param.put("COMMNO", "00001");
        return sqlSessionTemplate.selectList(NAMESPACE + "SELECT_COMM_TEST", param);
    }




}
