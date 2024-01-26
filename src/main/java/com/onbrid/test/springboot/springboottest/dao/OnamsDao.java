package com.onbrid.test.springboot.springboottest.dao;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@DependsOn(value = {"onamsSqlSession"})
@Primary
@Slf4j
public class OnamsDao extends OnBridDao {

    @Autowired
    public OnamsDao(@Qualifier("onamsSqlSession") SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    void setNameSpace(String sub) {}

    @Override
    public List queryForList(String sqlId) {
        log.debug("OnAMS Dao.queryForList");
        return sqlSessionTemplate.selectList(NAMESPACE + sqlId);
    }

    public List customMethod() {
        // 어떤 작업...
        log.debug("OnAMS Dao Custom Method");

        Map param = new HashMap();
        param.put("COMMNO", "00001");
        return sqlSessionTemplate.selectList(NAMESPACE + "SELECT_TEST", param);
    }




}
