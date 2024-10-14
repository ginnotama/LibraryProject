package com.demo.library.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.library.dto.QueryBooks;
import com.demo.library.pojo.Book;
import com.demo.library.pojo.Result;
import com.demo.library.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yogurt
 * @since 2024-10-14
 */
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @PostMapping("/books")
    public Result<IPage<Book>> getBook(@Validated @RequestBody QueryBooks queryBooksParam){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if(StringUtils.hasText(queryBooksParam.getName())){
            queryWrapper.lambda().like(Book::getBookName, queryBooksParam.getName());
        }
        if(StringUtils.hasText(queryBooksParam.getType())){
            queryWrapper.lambda().eq(Book::getTypeId, queryBooksParam.getType());
        }
        IPage<Book> page = new Page<>(Integer.parseInt(queryBooksParam.getPageNum()),Integer.parseInt( queryBooksParam.getPageSize()));
        IPage<Book> bookIPage = bookService.page(page, queryWrapper);

        return Result.success(bookIPage);
    }
}

