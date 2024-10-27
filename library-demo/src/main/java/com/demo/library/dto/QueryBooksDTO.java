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
@ApiModel("图书列表查询")
public class QueryBooksDTO {
    @ApiModelProperty(value = "书名", required = false)
    private String name;
    @ApiModelProperty(value = "类型", required = false)
    private String type;

    @ApiModelProperty(value = "分页请求页码", required = true)
    @NotNull( message = "分页请求页码不可为空")
    private String pageNum;

    @ApiModelProperty(value = "分页请求页数", required = true)
    @NotNull( message = "分页请求页数不可为空")
    private String pageSize;

}
