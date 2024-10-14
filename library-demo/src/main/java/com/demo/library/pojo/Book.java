package com.demo.library.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书籍id
     */
    @TableId(value = "book_id", type = IdType.AUTO)
    private Long bookId;

    /**
     * 书籍编号
     */
    private String bookNum;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍作者
     */
    private String bookAuthor;

    /**
     * 书籍描述
     */
    private String bookDesc;

    /**
     * 书籍位置
     */
    private String bookLocation;

    /**
     * 书籍类型id
     */
    private Long typeId;

    /**
     * 书籍状态 0可借阅 1已借阅 2丢失等等
     */
    private Integer bookStatus;

    /**
     * 书籍创建时间
     */
    private LocalDateTime bookCreateTime;

    /**
     * 书籍更新时间
     */
    private LocalDateTime bookUpdateTime;


}
