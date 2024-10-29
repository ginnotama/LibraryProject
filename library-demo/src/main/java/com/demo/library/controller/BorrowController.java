package com.demo.library.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.library.dto.BorrowDTO;
import com.demo.library.dto.BorrowWithNameDTO;
import com.demo.library.dto.QueryBorrowDTO;
import com.demo.library.enums.BookState;
import com.demo.library.enums.BorrowState;
import com.demo.library.pojo.Book;
import com.demo.library.pojo.Borrow;
import com.demo.library.pojo.Result;
import com.demo.library.pojo.User;
import com.demo.library.service.impl.BookServiceImpl;
import com.demo.library.service.impl.BorrowServiceImpl;
import com.demo.library.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yogurt
 * @since 2024-10-14
 */
@RestController
@RequestMapping("/borrow")
@Api(value = "借阅模块接口", tags = "借阅模块接口")
@Transactional(rollbackFor = Exception.class)
public class BorrowController {
    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private BorrowServiceImpl borrowService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("addBorrow")
    @ApiOperation(value = "借阅书籍", notes = "传入借阅书籍id, 用户id, 借阅截止时间")
    public synchronized  Result<?> addBorrow(@RequestBody @Validated BorrowDTO borrowDTO){
        User user = userService.getById(borrowDTO.getUserId());
        if(user == null){
            return Result.error("当前用户不存在, 请核查上传用户 id");
        }

        Book book = bookService.getById(borrowDTO.getBookId());
        if(book == null){
            return Result.error("当前书籍不存在, 请核查上传书籍 id");
        }

        if(!book.getBookStatus().equals(BookState.BORROWABLE)){
            return Result.error("当前图书不可借阅(已被借阅或书籍状态异常)");
        }
        //更新书籍状态
        book.setBookStatus(BookState.NOT_BORROWABLE);
        bookService.updateById(book);

        //创建借阅信息
        Borrow borrow = new Borrow();
        borrow.setUserId(borrowDTO.getUserId());
        borrow.setBookId(borrowDTO.getBookId());
        borrow.setEndDate(borrowDTO.getEndDate());
        //借阅开始时间后端生成
        borrow.setBorrowDate(LocalDateTime.now());
        borrow.setCreateTime(LocalDateTime.now());
        //借阅状态  1 借阅完成 0 借阅中
        borrow.setBorrowState(BorrowState.NOT_RETURNED);
        borrowService.save(borrow);

        return Result.success();
    }

    @PostMapping("/returnBook")
    @ApiOperation(value = "归还书籍", notes = "传入借阅id(borrowId)即可")
    public Result<?> returnBook(@RequestBody @Validated Borrow borrowDTO){
        Borrow borrow = borrowService.getById(borrowDTO.getBorrowId());
        if(borrow == null){
            return Result.error("当前借阅信息不存在, 请核查上传借阅 id");
        }
        if(BorrowState.RETURNED.equals(borrow.getBorrowState())){
            return Result.error("当前借阅已归还,无需重复归还");
        }

        //更新归还表状态
        borrow.setBorrowState(BorrowState.RETURNED);
        borrow.setReturnDate(LocalDateTime.now());
        borrowService.updateById(borrow);

        //书籍借阅状态, 可借阅
        Book book = bookService.getById(borrow.getBookId());
        book.setBookStatus(BookState.BORROWABLE);
        bookService.updateById(book);
        return Result.success();
    }

    @PostMapping("getBorrows")
    @ApiOperation(value = "查询借阅信息", notes = "查询借阅信息(支持传入用户id或书籍id, 单独查询某个用户所有借阅信息或某本书籍所有借阅信息)")
    public Result<IPage<BorrowWithNameDTO>> getBorrows(@RequestBody @Validated QueryBorrowDTO queryBorrowDTO){
        LambdaQueryWrapper<Borrow> borrowWrapper = new LambdaQueryWrapper<>();
        if(queryBorrowDTO.getUserId() != null){
            borrowWrapper.eq(Borrow::getUserId  ,queryBorrowDTO.getUserId());
        }

        if(queryBorrowDTO.getBookId() != null){
            borrowWrapper.eq(Borrow::getBookId  ,queryBorrowDTO.getBookId());
        }

        IPage<Borrow> page = new Page<>(Integer.parseInt(queryBorrowDTO.getPageNum()),Integer.parseInt( queryBorrowDTO.getPageSize()));
        page = borrowService.page(page, borrowWrapper);
        List<Borrow> borrows = page.getRecords();

        ArrayList<BorrowWithNameDTO> borrowDTOList= new ArrayList<>();

        for (Borrow borrow : borrows) {
            BorrowWithNameDTO borrowWithNameDTO = new BorrowWithNameDTO();
            BeanUtils.copyProperties(borrow, borrowWithNameDTO);

            Long bookId = borrow.getBookId();
            Book book = bookService.getById(bookId);
            borrowWithNameDTO.setBookName(book.getBookName());

            borrowDTOList.add(borrowWithNameDTO);
        }

        IPage<BorrowWithNameDTO> withNameDTOPage = new Page<>();
        withNameDTOPage.setRecords(borrowDTOList);
        withNameDTOPage.setPages(page.getPages());
        withNameDTOPage.setCurrent(page.getCurrent());
        withNameDTOPage.setSize(page.getSize());
        withNameDTOPage.setTotal(page.getTotal());

        return Result.success(withNameDTOPage);

    }
}

