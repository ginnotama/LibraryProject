package com.demo.library.aop;

import com.alibaba.fastjson.JSON;
import com.demo.library.pojo.Result;
import com.demo.library.utils.ReqUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : yogurt-z
 * @date : 2024/10/28
 */
@Aspect
@Component
@Slf4j
public class LogAop {
    @Pointcut("execution(* com.demo.library.controller..*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object validCodeAop(ProceedingJoinPoint joinPoint) {
        HttpServletRequest req = ReqUtil.getReq();
        String reqParam = ReqUtil.getReqParam(req);
        log.info("请求参数 : " + reqParam);
        MDC.put("ip", req.getRemoteAddr());
        MDC.put("mvc", req.getRequestURI());

        try {
            Object proceed = joinPoint.proceed();
            log.info("接口响应信息 : " + JSON.toJSON(proceed));
            return proceed;
        } catch (Throwable e) {
            e.printStackTrace();
            return Result.error();
        }
    }

}
