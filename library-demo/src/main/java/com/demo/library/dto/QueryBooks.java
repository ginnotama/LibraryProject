package com.demo.library.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author : yogurt-z
 * @date : 2024/10/14
 */
@Data
@Valid
public class QueryBooks {
    private String name;
    private String type;
    @NotNull( message = "分页请求页码不可为空")
    private String pageNum;
    @NotNull( message = "分页请求页数不可为空")
    private String pageSize;

}
