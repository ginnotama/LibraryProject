package com.demo.library.config;

import com.demo.library.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author : yogurt-z
 * @date : 2024/10/14
 * 统一异常处理, 避免错误暴露前端
 */
@RestControllerAdvice
@Slf4j
public class ComExceptionAdvice  {
    @ExceptionHandler(Exception.class)
    public Result<?> commonExceptionHandler(Exception e){
        log.info(e.getMessage(), e);
        return Result.error("服务器异常, 请稍后在试~");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> argumentExceptionHandler(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            if(!errors.isEmpty()){
                log.info("请求参数异常", errors.get(0).getDefaultMessage());
                return Result.error(errors.get(0).getDefaultMessage());
            }
        }
        return Result.error("请求类型或参数异常");
    }
}
