package com.demo.library.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yogurt-z
 * @date : 2024/10/15
 */
@Component
@Slf4j
public class UserSession {
    private static final String userKey = "user";

    public  HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpSession getSession(){
        HttpServletRequest request = getRequest();
        return request.getSession();
    }

    public Object getUserInfo(String key){
        try{
            HttpSession session = getSession();
            if(session.getAttribute(userKey) == null){
                session.setAttribute(userKey, new HashMap<String,Object>());
            }
            Map<String, Object> userMap= (Map<String, Object>) session.getAttribute(userKey);
            return userMap.get(key);
        }catch (NullPointerException e){
            log.info("用户信息为空!");
            return null;
        }

    }

    public void setUserInfo(String key, Object val){
        HttpSession session = getSession();
        if(session.getAttribute(userKey) == null){
            session.setAttribute(userKey, new HashMap<String,Object>());
        }
        Map<String, Object> userMap= (Map<String, Object>) session.getAttribute(userKey);
        userMap.put(key, val);
    }

    public void clearUserInfo(){
        HttpSession session = getSession();
        if(session.getAttribute(userKey) == null){
            session.setAttribute(userKey, new HashMap<String,Object>());
        }
        Map<String, Object> userMap= (Map<String, Object>) session.getAttribute(userKey);
        userMap.clear();
    }

}
