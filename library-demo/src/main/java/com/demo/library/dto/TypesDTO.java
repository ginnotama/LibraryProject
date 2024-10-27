package com.demo.library.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author : yogurt-z
 * @date : 2024/10/18
 */
@ApiModel
@Data
public class TypesDTO {
    @ApiModelProperty(value = "分页请求页码", required = true)
    @NotNull( message = "分页请求页码不可为空")
    private String pageNum;

    @ApiModelProperty(value = "分页请求页数", required = true)
    @NotNull( message = "分页请求页数不可为空")
    private String pageSize;

    @ApiModelProperty(value = "书籍类型id", required = false)
    private Long typeId;
}
