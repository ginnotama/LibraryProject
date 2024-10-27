package com.demo.library.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author : yogurt-z
 * @date : 2024/10/14
 */
@Data
@Valid
@ApiModel
public class LoginUserDTO {
    @NotNull( message = "登陆名不可为空")
    @ApiModelProperty(value = "登陆名", required = false)
    private String userLoginName;

    @NotNull( message = "登录密码不可为空")
    @ApiModelProperty(value = "登录密码", required = false)
    private String password;

    @NotNull( message = "登录验证码不可为空")
    @ApiModelProperty(value = "登录验证码", required = false)
    private String code;
}
