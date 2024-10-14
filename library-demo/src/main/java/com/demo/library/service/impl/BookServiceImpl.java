package com.demo.library.service.impl;

import com.demo.library.pojo.Book;
import com.demo.library.dao.BookDao;
import com.demo.library.service.IBookService;
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
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {

}
