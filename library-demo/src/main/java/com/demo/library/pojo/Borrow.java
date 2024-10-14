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
public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 借阅id
     */
    @TableId(value = "borrow_id", type = IdType.AUTO)
    private Long borrowId;

    /**
     * 书籍id
     */
    private Long bookId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 借阅状态 1 借阅完成 0 借阅中
     */
    private Integer borrowState;

    /**
     * 借阅时间
     */
    private LocalDateTime borrowDate;

    /**
     * 截止时间
     */
    private LocalDateTime endDate;

    /**
     * 归还时间
     */
    private LocalDateTime returnDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
