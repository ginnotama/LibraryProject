package com.demo.library.pojo;

/**
 * @author : yogurt-z
 * @date : 2024/10/14
 */
public enum RetCode {
    /**
     * 请求响应码
     */
    SUCCESS("200","请求成功!"),
    ERROR("400", "请求出错啦~"),
    CODE_ERROR("400", "验证码错误"),
    PASS_ERROR("400", "密码错误"),
    STATE_ERROR("400", "用户状态禁止登录"),
    Login_ERROR("400", "用户不存在");

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
