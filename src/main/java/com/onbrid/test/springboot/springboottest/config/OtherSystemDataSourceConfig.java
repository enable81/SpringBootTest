package com.onbrid.test.springboot.springboottest.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@PropertySource("classpath:config/onams-${spring.profiles.active}.properties")
@Slf4j
public class OtherSystemDataSourceConfig {

    @Value("${database.mapper-dir.other}")
    String database;

    @Bean(name = "othrsysDataSource")
    @ConfigurationProperties(prefix = "spring.otherdb.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "othrsysSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Autowired @Qualifier("othrsysDataSource") DataSource dataSource, ApplicationContext applicationContext)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/sql/" + database + "/**/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name="othrsysSqlSession")
    public SqlSessionTemplate sqlSession(@Autowired @Qualifier("othrsysSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "othrsysTransactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("othrsysDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
