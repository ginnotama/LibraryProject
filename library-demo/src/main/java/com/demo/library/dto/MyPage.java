package com.demo.library.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : yogurt-z
 * @date : 2024/10/15
 */
@ApiModel
@Data
public class MyPage<T> {
    @ApiModelProperty(value = "总数", required = false)
    private String total;
    @ApiModelProperty(value = "页数", required = false)
    private String size;
    @ApiModelProperty(value = "当前页", required = false)
    private String current;
    @ApiModelProperty(value = "分页数据", required = false)
    private T list;
}
