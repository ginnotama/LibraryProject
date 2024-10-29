package com.demo.library.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author : yogurt-z
 * @date : 2024/10/28
 */
public class ReqUtil {
    public static HttpServletRequest getReq(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getReqParam(HttpServletRequest req){
        if(req instanceof ContentCachingRequestWrapper){
            ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) req;
            byte[] byteArray = requestWrapper.getContentAsByteArray();
            return new String(byteArray, StandardCharsets.UTF_8).replaceAll("[\\s]", "");
        }
        return null;
    }
}
