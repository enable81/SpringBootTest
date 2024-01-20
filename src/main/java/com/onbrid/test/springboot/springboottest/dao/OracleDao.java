package com.onbrid.test.springboot.springboottest.dao;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@DependsOn(value = {"oracleSqlSession"})
@Slf4j
@Primary
@Profile(value = {"db-oracle"})
public class OracleDao extends OnamsDao {

    @Autowired
    public OracleDao(@Qualifier("oracleSqlSession") SqlSessionTemplate sqlSessionTemplate) {
        super(sqlSessionTemplate);
        log.debug("Oracle Dao init...");
    }


    @Override
    public List queryForList(String sqlId) {
        log.debug("Oracle Dao.queryForList");
        return sqlSessionTemplate.selectList(NAMESPACE + sqlId);
    }

    public List customMethod() {
        // 어떤 작업...
        log.debug("OracleDao Custom Method");

        Map param = new HashMap();
        param.put("COMMNO", "00001");
        return sqlSessionTemplate.selectList(NAMESPACE + "SELECT_COMM_TEST", param);
    }

}
