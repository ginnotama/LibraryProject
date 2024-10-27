package com.demo.library.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author : yogurt-z
 * @date : 2024/10/15
 */
@Data
@Valid
@ApiModel
public class RegisterUserDTO {
    /**
     * 登录名
     */
    @NotNull(message = "登录名不可为空")
    @ApiModelProperty(value = "登录名", required = true)

    private String userLoginName;

    /**
     * 登录密码
     */
    @NotNull(message = "登录密码不可为空")
    @ApiModelProperty(value = "登录密码", required = true)

    private String userPassword;

    /**
     * 真实姓名
     */
    @NotNull(message = "真实姓名不可为空")
    @ApiModelProperty(value = "真实姓名", required = true)

    private String userName;

    @NotNull( message = "登录验证码不可为空")
    @ApiModelProperty(value = "验证码", required = true)

    private String code;
}
