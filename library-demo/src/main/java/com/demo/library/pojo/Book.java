package com.demo.library.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书籍id
     */
    @TableId(value = "book_id", type = IdType.AUTO)
    @ApiModelProperty(value = "书籍id", required = false)
    private Long bookId;

    /**
     * 书籍编号
     */
    @ApiModelProperty(value = "书籍编号", required = true)
    @NotNull(message = "书籍编号 不可为空")
    private String bookNum;

    /**
     * 书籍名称
     */
    @ApiModelProperty(value = "书籍名称", required = true)
    @NotNull(message = "书籍名称 不可为空")
    private String bookName;

    /**
     * 书籍作者
     */
    @ApiModelProperty(value = "书籍作者", required = true)
    @NotNull(message = "书籍作者 不可为空")
    private String bookAuthor;

    /**
     * 书籍描述
     */
    @ApiModelProperty(value = "书籍描述", required = true)
    @NotNull(message = "书籍描述 不可为空")
    private String bookDesc;

    /**
     * 书籍位置
     */
    @ApiModelProperty(value = "书籍位置", required = true)
    @NotNull(message = "书籍位置 不可为空")
    private String bookLocation;

    /**
     * 书籍类型id
     */
    @ApiModelProperty(value = "书籍类型id", required = true)
    @NotNull(message = "书籍类型id 不可为空")
    private Long typeId;

    /**
     * 书籍状态 0可借阅 1停止借阅
     */
    @ApiModelProperty(value = "书籍状态 0可借阅 1停止借阅 ")
    private Integer bookStatus;

    /**
     * 书籍创建时间
     */
    @ApiModelProperty(value = "书籍创建时间, 后端维护")
    private LocalDateTime bookCreateTime;

    /**
     * 书籍更新时间
     */
    @ApiModelProperty(value = "书籍更新时间, , 后端维护")
    private LocalDateTime bookUpdateTime;

}
