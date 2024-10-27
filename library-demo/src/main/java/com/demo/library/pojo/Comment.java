package com.demo.library.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
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
@Valid
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id自增主键
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    @ApiModelProperty(value = "评论id(新增评论时无需传入, 后端生成, 查询评论需要用)", required = true)
    private Long commentId;

    /**
     * 评论详情
     */
    @ApiModelProperty(value = "评论详情)", required = true)
    @NotNull(message = "评论详情 不可为空")
    private String commentDesc;

    /**
     * 评论状态 0 评论不可见 1 评论可见  2 评论删除
     */
    @ApiModelProperty(value = "评论状态, 目前该字段未启用", required = false)
    private Integer commentState;

    /**
     * 评论创建时间
     */
    @ApiModelProperty(value = "评论创建时间, 后端生成)", required = false)
    private LocalDateTime commentCreateTime;

    /**
     * 书籍id
     */
    @ApiModelProperty(value = "评论对应的 书籍id)", required = true)
    @NotNull(message = "评论对应的 书籍id 不可为空")
    private Long bookId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "评论对应的 用户id)", required = true)
    @NotNull(message = "评论对应的 用户id 不可为空")
    private Long userId;


}
