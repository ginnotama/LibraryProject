package com.demo.library.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yogurt
 * @since 2024-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增Id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键自增Id", required = false)
    @NotNull( message = "用户id 不可为空")
    private Long userId;

    /**
     * 登录用户名
     */
    @ApiModelProperty(value = "登录用户名", required = false)
    @NotNull( message = "登录用户名 不可为空")
    private String userLoginName;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码", required = false)
    @NotNull( message = "登录密码 不可为空")
    private String userPassword;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名", required = false)
    @NotNull( message = "真实姓名 不可为空")
    private String userName;

    /**
     * 账号状态 0正常,1异常
     */
    @ApiModelProperty(value = " 账号状态 0正常,1异常", required = false)
    @NotNull( message = "账号状态 不可为空")
    private Integer userStatus;

    /**
     * 账号类型(0普通,1管理员)
     */
    @ApiModelProperty(value = "账号类型(0普通,1管理员)", required = false)
    @NotNull( message = "账号类型 不可为空")
    private Integer userType;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false)

    private LocalDateTime userCreateTime;


}
