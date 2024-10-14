package com.demo.library.service.impl;

import com.demo.library.pojo.Comment;
import com.demo.library.dao.CommentDao;
import com.demo.library.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yogurt
 * @since 2024-10-14
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements ICommentService {

}
