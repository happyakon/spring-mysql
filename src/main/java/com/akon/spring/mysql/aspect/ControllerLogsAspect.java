package com.akon.spring.mysql.aspect;

import com.akon.spring.mysql.annotation.ControllerLogs;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Configuration
public class ControllerLogsAspect {

    @Pointcut("@annotation(com.akon.spring.mysql.annotation.ControllerLogs)")
    public void controllerLogs() {}

    @Before("controllerLogs()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("Request URL  :{}",request.getRequestURI());
        log.info("HTTP Method  :{}",request.getMethod());
        log.info("Description  :{}",getDescription(joinPoint));
        log.info("Class Method :{}.{} begin",
                joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if(args==null){
            log.info("Request Args :{}", JSON.toJSONString(joinPoint.getArgs()));
        }else{
            log.info("Request Args :{}", "[]");
        }

    }



    @AfterReturning(value = "controllerLogs()",returning ="result" )
    public void doAfter(JoinPoint joinPoint, Object result)  {
        log.info("Class Method :{}.{} end",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        log.info("Response Args :{}", JSON.toJSONString(result));
    }


    /**
     * 获取注解描述详情
     * @param joinPoint 切入点
     * @return
     * @throws Exception
     */
    public static String getDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder();
        for (Method method : methods) {
            if (method.getName().equals(methodName)){
                Class<?>[] classes = method.getParameterTypes();
                if (classes.length == args.length){
                    description.append(method.getAnnotation(ControllerLogs.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }

}
