package com.demo.library.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author : yogurt-z
 * @date : 2024/10/14
 */
@Data
@RequiredArgsConstructor
public class Result <T> {
    String code;
    String msg;
    T date;

    public Result(RetCode code, T date) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.date = date;
    }

    public Result(T date) {
        this.date = date;
    }

    public Result(RetCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public static <D> Result<D>  success(D date){
        return new Result<>(RetCode.SUCCESS, date);
    }

    public static <D> Result<D>  success(){
        return new Result<>(RetCode.SUCCESS);
    }
    public static <D> Result<D>  error(D date){
        return new Result<>(RetCode.ERROR, date);
    }
    public static <D> Result<D>  error(){
        return new Result<>(RetCode.ERROR);
    }
    public static <D> Result<D>  error(RetCode code){
        return new Result<>(code);
    }
}
