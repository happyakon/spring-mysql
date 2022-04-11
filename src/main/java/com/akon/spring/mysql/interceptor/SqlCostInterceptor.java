package com.akon.spring.mysql.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Properties;

/**
 * mybatis 拦截器
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
@Component
public class SqlCostInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;
            log.info("SQL CostTime:[ {} ]ms",costTime);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
