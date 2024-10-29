package com.demo.library.dto;

import com.demo.library.pojo.Borrow;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : yogurt-z
 * @date : 2024/10/29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BorrowWithNameDTO extends Borrow{
    @ApiModelProperty(value = "书籍名称", required = true)
    private String bookName;
}
