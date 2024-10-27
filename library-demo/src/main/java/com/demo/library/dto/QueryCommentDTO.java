package com.demo.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : yogurt-z
 * @date : 2024/10/27
 */
@Data
public class QueryCommentDTO {
    @ApiModelProperty(value = "书籍id")
    private Long bookId;

    @ApiModelProperty(value = "用户id")
    private Long userId;


    @ApiModelProperty(value = "分页请求页码", required = true)
    @NotNull( message = "分页请求页码不可为空")
    private String pageNum;

    @ApiModelProperty(value = "分页请求页数", required = true)
    @NotNull( message = "分页请求页数不可为空")
    private String pageSize;

}
