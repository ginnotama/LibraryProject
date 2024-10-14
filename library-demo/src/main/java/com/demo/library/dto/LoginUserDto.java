package com.demo.library.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author : yogurt-z
 * @date : 2024/10/14
 */
@Data
@Valid
public class LoginUserDto {
    @NotNull( message = "登陆名不可为空")
    private String userLoginName;
    @NotNull( message = "登录密码不可为空")
    private String password;
    @NotNull( message = "登录验证码不可为空")
    private String code;
}
