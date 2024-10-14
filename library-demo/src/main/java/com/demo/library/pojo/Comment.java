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
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id自增主键
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 评论详情
     */
    private String commentDesc;

    /**
     * 评论状态 0 评论不可见 1 评论可见  2 评论删除
     */
    private Integer commentState;

    /**
     * 评论创建时间
     */
    private LocalDateTime commentCreateTime;

    /**
     * 书籍id
     */
    private Long bookId;

    /**
     * 用户id
     */
    private Long userId;


}
