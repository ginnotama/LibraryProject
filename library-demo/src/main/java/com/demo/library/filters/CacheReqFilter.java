package com.demo.library.filters;

import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author : yogurt-z
 * @date : 2024/10/28
 */
@WebFilter(filterName = "cacheFilter")
public class CacheReqFilter implements Filter {
    private static final String MULTIPART = "multipart/form-data";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest){
            String contentType = servletRequest.getContentType();
            if(contentType == null || !contentType.contains(MULTIPART)){
                servletRequest = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
