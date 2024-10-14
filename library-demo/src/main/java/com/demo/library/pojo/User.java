package com.demo.library.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键自增Id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 登录用户名
     */
    private String userLoginName;

    /**
     * 登录密码
     */
    private String userPassword;

    /**
     * 真实姓名
     */
    private String userName;

    /**
     * 账号状态
     */
    private Integer userStatus;

    /**
     * 账号类型(0普通,1管理员)
     */
    private Integer userType;

    /**
     * 创建时间
     */
    private LocalDateTime userCreateTime;


}
