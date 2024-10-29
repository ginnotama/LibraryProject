package com.demo.library.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.library.dto.QueryCommentDTO;
import com.demo.library.enums.CommentState;
import com.demo.library.pojo.Book;
import com.demo.library.pojo.Comment;
import com.demo.library.pojo.Result;
import com.demo.library.pojo.User;
import com.demo.library.service.impl.BookServiceImpl;
import com.demo.library.service.impl.CommentServiceImpl;
import com.demo.library.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yogurt
 * @since 2024-10-14
 */
@RestController
@RequestMapping("/comment")
@Transactional(rollbackFor = Exception.class)
@Api(value = "评论模块接口", tags = "评论模块接口")
public class CommentController {
    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/addComment")
    @ApiOperation(value = "发布评论信息", notes = "发布评论信息)")
    public Result<?> addComment(@RequestBody @Validated Comment comment){
        comment.setCommentId(null);
        User user = userService.getById(comment.getUserId());
        if(user == null){
            return Result.error("当前用户不存在, 请核查用户 id");
        }

        Book book = bookService.getById(comment.getBookId());
        if(book == null){
            return Result.error("当前 书籍 不存在, 请核查 书籍 id");
        }

        comment.setCommentState(CommentState.VISIABLE);
        comment.setCommentCreateTime(LocalDateTime.now());
        commentService.save(comment);
        return Result.success();
    }

    @PostMapping("getComments")
    @ApiOperation(value = "查询评论信息", notes = "查询评论信息(支持传入用户id或书籍id, 单独查询某个用户所有借阅信息或某本书籍 所有 查询评论信息)")
    public Result<IPage<Comment>> getBorrows(@RequestBody @Validated QueryCommentDTO commentDTO){
        LambdaQueryWrapper<Comment> commentWrapper = new LambdaQueryWrapper<>();
        if(commentDTO.getUserId() != null){
            commentWrapper.eq(Comment::getUserId  ,commentDTO.getUserId());
        }

        if(commentDTO.getBookId() != null){
            commentWrapper.eq(Comment::getBookId  ,commentDTO.getBookId());
        }

        IPage<Comment> page = new Page<>(Integer.parseInt(commentDTO.getPageNum()),Integer.parseInt( commentDTO.getPageSize()));
        page = commentService.page(page, commentWrapper);

        return Result.success(page);
    }

}

