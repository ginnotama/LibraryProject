package com.demo.library.aop;

import com.demo.library.annotation.ValidCode;
import com.demo.library.pojo.Result;
import com.demo.library.pojo.RetCode;
import com.demo.library.utils.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : yogurt-z
 * @date : 2024/10/15
 */
@Aspect
@Component
@Slf4j
public class ValidCodeAop {
    @Pointcut("execution(* com.demo.library.controller..*(..)) && @annotation(com.demo.library.annotation.ValidCode)")
    public void codePointCut() {
    }

    @Autowired
    UserSession userSession;

    @Around("codePointCut()")
    public Object validCodeAop(ProceedingJoinPoint joinPoint) {
        log.info("验证码 AOP 处理开始");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ValidCode validCode = method.getAnnotation(ValidCode.class);
        //所需鉴权字段
        String codeField = validCode.value();

        Object arg = joinPoint.getArgs()[0];
        Class<?> aClass = arg.getClass();
        try {
            Field field = aClass.getDeclaredField(codeField);
            field.setAccessible(true);
            String code = (String) field.get(arg);
            String sessionCode = (String) userSession.getUserInfo("code");
            if (!code.equals(sessionCode)) {
                return Result.error(RetCode.CODE_ERROR);
            }
        } catch (NoSuchFieldException e) {
            log.info("配置验证字段缺失", e);
            return Result.error("AOP执行失败");
        } catch (IllegalAccessException e) {
            log.info("字段权限访问失败", e);
            return Result.error("AOP执行失败");
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.info("AOP原方法执行异常", e);
            return Result.error();
        }
    }
}
