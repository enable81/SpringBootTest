package com.onbrid.test.springboot.springboottest.config;

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

@Configuration
@PropertySource("classpath:config/onams-${spring.profiles.active}.properties")
public class OnamsDataSourceConfig {

    @Value("${database.mapper-dir.onams}")
    String database;

    @Primary
    @Bean(name = "onamsDataSource")
    @ConfigurationProperties(prefix = "spring.onams.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "onamsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Autowired @Qualifier("onamsDataSource") DataSource dataSource, ApplicationContext applicationContext)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:/sql/onams/" + database + "/**/*.xml"));
        return factoryBean.getObject();
    }

    @Primary
    @Bean(name="onamsSqlSession")
    public SqlSessionTemplate sqlSession(@Autowired @Qualifier("onamsSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "onamsTransactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired @Qualifier("onamsDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
