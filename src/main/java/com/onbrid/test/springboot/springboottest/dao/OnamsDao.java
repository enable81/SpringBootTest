package com.onbrid.test.springboot.springboottest.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public abstract class OnamsDao {

    protected static String NAMESPACE = "sql-mapper.";

    SqlSessionTemplate sqlSessionTemplate;

    public  int delete(String sqlId)
    {
        return sqlSessionTemplate.delete(NAMESPACE + sqlId);
    }

    public  int delete(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.delete(NAMESPACE + sqlId, rowMap);
    }

    public  int insert(String sqlId)
    {
        return sqlSessionTemplate.insert(NAMESPACE + sqlId);
    }

    public  int insert(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.insert(NAMESPACE + sqlId, rowMap);
    }

    public  int update(String sqlId)
    {
        return sqlSessionTemplate.update(NAMESPACE + sqlId);
    }

    public  int update(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.update(NAMESPACE + sqlId, rowMap);
    }

    public  int queryForInt(String sqlId, Map rowMap)
    {
        Integer intVal = (Integer)sqlSessionTemplate.selectOne(NAMESPACE + sqlId, rowMap);
        return intVal.intValue();
    }

    public  Map queryForMap(String sqlId, Map rowMap)
    {
        return (Map)sqlSessionTemplate.selectOne(NAMESPACE + sqlId, rowMap);
    }

    public  Map queryForMap(String sqlId, Map rowMap, String keyValue)
    {
        return (Map)sqlSessionTemplate.selectMap(NAMESPACE + sqlId, rowMap, keyValue);
    }

    public List queryForList(String sqlId, Map rowMap)
    {
        return sqlSessionTemplate.selectList(NAMESPACE + sqlId, rowMap);
    }

    public List queryForList(String sqlId)
    {
        return sqlSessionTemplate.selectList(NAMESPACE + sqlId);
    }

    public  String queryForStr(String sqlId, Map rowMap)
    {
        return (String)sqlSessionTemplate.selectOne(NAMESPACE + sqlId, rowMap);
    }

    protected void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

}
