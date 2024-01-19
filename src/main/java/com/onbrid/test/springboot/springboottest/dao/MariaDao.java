package com.onbrid.test.springboot.springboottest.dao;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn(value = {"mariaSqlSession"})
@Slf4j
public class MariaDao extends OnamsDao {

    @Autowired
    public MariaDao(@Qualifier("mariaSqlSession") SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    public List customMethod() {
        // 어떤 작업...
        log.debug("Dao Custom Method");

        return sqlSessionTemplate.selectList(NAMESPACE + "SELECT_COMM_TEST");
    }

}
