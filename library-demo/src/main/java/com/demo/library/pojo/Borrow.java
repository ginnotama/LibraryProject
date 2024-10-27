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
public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 借阅id
     */
    @TableId(value = "borrow_id", type = IdType.AUTO)
    @ApiModelProperty(value = "借阅id" , required = true)
    @NotNull(message = "借阅id 不可为空")
    private Long borrowId;

    /**
     * 书籍id
     */
    @ApiModelProperty(value = "书籍id" , required = false)
    private Long bookId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id" , required = false)
    private Long userId;

    /**
     * 借阅状态 1 借阅完成 0 借阅中
     */
    @ApiModelProperty(value = "借阅状态, 后端生成" , required = false)
    private Integer borrowState;

    /**
     * 借阅时间
     */
    @ApiModelProperty(value = "借阅时间, 后端生成" , required = false)
    private LocalDateTime borrowDate;

    /**
     * 截止时间
     */
    @ApiModelProperty(value = "截止时间, 后端生成" , required = false)
    private LocalDateTime endDate;

    /**
     * 归还时间
     */
    private LocalDateTime returnDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
