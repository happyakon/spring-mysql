package com.akon.spring.mysql.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.LocalCacheScope;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@Slf4j
public class MybatisConfig {

    @Autowired
    private DataSource dataSource;


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBeanGwcmp() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /**
         * 关闭一级缓存
         */
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
        bean.setConfiguration(configuration);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String path ="classpath*:com/akon/spring/mysql/mapper/*Mapper.xml";
        Resource[] resources = new Resource[0];
        try {
            resources = resolver.getResources(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bean.setMapperLocations(resources);
        try {
            log.info("bean creat success Mapper [{}]",resources.length);
            return bean;
        } catch (Exception e) {
            log.info("bean creat error!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
