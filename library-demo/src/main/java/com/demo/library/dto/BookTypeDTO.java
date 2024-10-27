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
public class BookTypeDTO {
    @ApiModelProperty(value = "书籍类型id", required = true)
    @NotNull( message = "书籍类型id 不可为空")
    private Long typeId;
}
