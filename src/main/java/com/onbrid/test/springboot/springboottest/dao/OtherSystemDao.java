package com.onbrid.test.springboot.springboottest.dao;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@DependsOn(value = {"othrsysSqlSession"})
@Slf4j
public class OtherSystemDao extends OnBridDao {

    @Autowired
    public OtherSystemDao(@Qualifier("othrsysSqlSession") SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.setNameSpace("os.");
    }

    @Override
    void setNameSpace(String sub) {
        super.NAMESPACE += sub;
    }

    public List customMethod() {
        // 어떤 작업...
        log.debug("Other System Custom Method");

        Map param = new HashMap();
        param.put("COMMNO", "00001");
        return sqlSessionTemplate.selectList(NAMESPACE + "SELECT_TEST", param);
    }

}
