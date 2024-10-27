package com.demo.library.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.library.dto.QueryBooksDTO;
import com.demo.library.pojo.Book;
import com.demo.library.pojo.Result;
import com.demo.library.pojo.Type;
import com.demo.library.service.impl.BookServiceImpl;
import com.demo.library.service.impl.TypeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
@RequestMapping("/book")
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Api(value = "图书模块接口", tags = "图书模块接口")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    TypeServiceImpl typeService;

    @ApiOperation(value = "图书列表查询", notes = "图书列表查询, 支持书名模糊查询")
    @PostMapping("/books")
    public Result<IPage<Book>> getBook(@Validated @RequestBody QueryBooksDTO queryBooksParam){
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

    @ApiOperation(value = "新增图书", notes = "新增图书")
    @PostMapping("addBook")
    public Result<?> addBook(@Validated @RequestBody Book book){
        Type type = typeService.getById(book.getTypeId());
        if(type == null){
            return Result.error("书籍类型不存在! 请核查上传书籍类型 id");
        }
        book.setBookId(null);
        book.setBookStatus(0);
        book.setBookCreateTime(LocalDateTime.now());
        book.setBookUpdateTime(LocalDateTime.now());
        bookService.save(book);
        return Result.success();
    }

    @ApiOperation(value = "更新图书", notes = "更新图书, 可更新图书借阅状态, 图书类型,图书描述,图书数量等一系列信息。需传入所需更新图书id")
    @PostMapping("updateBook")
    public Result<?> updateBook(@Validated @RequestBody Book book){
        if(bookService.getById(book.getBookId()) == null){
            return Result.error("当前更新书籍不存在, 请核查上传书籍 id");
        }

        Type type = typeService.getById(book.getTypeId());
        if(type == null){
            return Result.error("书籍类型不存在! 请核查上传书籍类型 id");
        }
        book.setBookUpdateTime(LocalDateTime.now());
        bookService.updateById(book);
        return Result.success();
    }



}

