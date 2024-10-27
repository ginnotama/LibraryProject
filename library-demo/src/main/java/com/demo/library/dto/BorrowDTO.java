package com.demo.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author : yogurt-z
 * @date : 2024/10/27
 */
@Data
public class BorrowDTO {
    /**
     * 书籍id
     */
    @ApiModelProperty(value = "书籍id" , required = true)
    @NotNull( message = "书籍id 不可为空")
    private Long bookId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id" , required = true)
    @NotNull( message = "用户id 不可为空")
    private Long userId;

    /**
     * 归还时间
     */
    @ApiModelProperty(value = "归还截止时间, 时间格式类型 2024-10-14T14:56:51" , required = true)
    @NotNull( message = "归还截止时间 不可为空")
    private LocalDateTime endDate;

}
