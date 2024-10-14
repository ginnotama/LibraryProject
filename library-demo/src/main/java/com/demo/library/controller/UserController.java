package com.demo.library.controller;


import com.demo.library.dto.LoginUserDto;
import com.demo.library.pojo.Result;
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
@RequestMapping("/user")
public class UserController {
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginUserDto userDto){

        return Result.success();
    }
}

