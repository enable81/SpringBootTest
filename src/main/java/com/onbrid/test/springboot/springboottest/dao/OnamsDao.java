package com.onbrid.test.springboot.springboottest.dao;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
public abstract class OnamsDao implements OnBridDao {

    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public OnamsDao(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        log.debug("Onams Dao init...");
    }


    @Override
    public  int delete(String sqlId) {
        return sqlSessionTemplate.delete(NAMESPACE + sqlId);
    }

    @Override
    public  int delete(String sqlId, Map rowMap) {
        return sqlSessionTemplate.delete(NAMESPACE + sqlId, rowMap);
    }

    @Override
    public  int insert(String sqlId) {
        return sqlSessionTemplate.insert(NAMESPACE + sqlId);
    }

    @Override
    public  int insert(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.insert(NAMESPACE + sqlId, rowMap);
    }

    @Override
    public  int update(String sqlId) {
        return sqlSessionTemplate.update(NAMESPACE + sqlId);
    }

    @Override
    public  int update(String sqlId, Map rowMap) {
        return sqlSessionTemplate.update(NAMESPACE + sqlId, rowMap);
    }

    @Override
    public  int queryForInt(String sqlId, Map rowMap) {
        Integer intVal = (Integer)sqlSessionTemplate.selectOne(NAMESPACE + sqlId, rowMap);
        return intVal.intValue();
    }

    @Override
    public  Map queryForMap(String sqlId, Map rowMap) {
        return (Map)sqlSessionTemplate.selectOne(NAMESPACE + sqlId, rowMap);
    }

    @Override
    public  Map queryForMap(String sqlId, Map rowMap, String keyValue) {
        return (Map)sqlSessionTemplate.selectMap(NAMESPACE + sqlId, rowMap, keyValue);
    }

    public List queryForList(String sqlId, Map rowMap) {
        return sqlSessionTemplate.selectList(NAMESPACE + sqlId, rowMap);
    }

    @Override
    public List queryForList(String sqlId) {
        return sqlSessionTemplate.selectList(NAMESPACE + sqlId);
    }

    @Override
    public  String queryForStr(String sqlId, Map rowMap) {
        return (String)sqlSessionTemplate.selectOne(NAMESPACE + sqlId, rowMap);
    }


}
