package com.onbrid.test.springboot.springboottest.dao;

import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

public interface OnBridDao {

    static String NAMESPACE = "sql-mapper.";

    public  int delete(String sqlId);

    public  int delete(String sqlId, Map rowMap);

    public  int insert(String sqlId);

    public  int insert(String sqlId, Map rowMap);

    public  int update(String sqlId);

    public  int update(String sqlId, Map rowMap);

    public  int queryForInt(String sqlId, Map rowMap);

    public  Map queryForMap(String sqlId, Map rowMap);

    public  Map queryForMap(String sqlId, Map rowMap, String keyValue);

    public List queryForList(String sqlId, Map rowMap);

    public List queryForList(String sqlId);

    public  String queryForStr(String sqlId, Map rowMap);


}
