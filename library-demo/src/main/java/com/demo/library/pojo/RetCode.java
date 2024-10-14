package com.demo.library.pojo;

import lombok.Data;

/**
 * @author : yogurt-z
 * @date : 2024/10/14
 */
public enum RetCode {
    /**
     * 请求响应码
     */
    SUCCESS("200","success"),
    ERROR("400", "error");

    private String code;
    private String msg;

    RetCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
