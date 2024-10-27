package com.demo.library.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.library.annotation.ValidCode;
import com.demo.library.dto.LoginUserDTO;
import com.demo.library.dto.RegisterUserDTO;
import com.demo.library.pojo.Result;
import com.demo.library.pojo.RetCode;
import com.demo.library.pojo.User;
import com.demo.library.service.impl.UserServiceImpl;
import com.demo.library.utils.UserSession;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
@RequestMapping("/user")
@Slf4j
@Api(value = "用户模块接口", tags = "用户模块接口")
@Transactional(rollbackFor = Exception.class)
public class UserController {

    @Autowired
    Producer kaptchaProducer;

    @Autowired
    UserSession userSession;

    @Autowired
    UserServiceImpl userService;

    /**
     *获取kaptcha验证码图片
     * @param request
     * @param response
     */
    @ApiOperation(value = "获取验证码(登录注册用)", notes = "获取验证码(返回image/png格式 流数据)")
    @PostMapping("/code")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        userSession.setUserInfo("code",text);
        response.setContentType("image/png");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e.getMessage(), e);
        }
    }

    @PostMapping("/login")
    @ValidCode("code")
    @ApiOperation(value = "登录", notes = "登录(返回登录成功用户相关信息)")
    public Result<User> login(@Validated  @RequestBody LoginUserDTO userDto){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserLoginName, userDto.getUserLoginName());
        User one = userService.getOne(queryWrapper);
        if(one == null) {
            return Result.error(RetCode.Login_ERROR);
        }

        if(!one.getUserPassword().equals(userDto.getPassword())) {
            return Result.error(RetCode.PASS_ERROR);
        }
        one.setUserPassword("");
        one.setUserId(0L);
        return Result.success(one);
    }

    @PostMapping("/registerUser")
    @ApiOperation(value = "普通用户注册", notes = "普通用户注册")
    @ValidCode("code")
    public Result<?> registerUser(@Validated  @RequestBody RegisterUserDTO registerUserDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserLoginName, registerUserDTO.getUserLoginName());
        User one = userService.getOne(queryWrapper);
        if(one != null) {
            return Result.error("登录名已被注册");
        }

        User user = new User();
        user.setUserLoginName(registerUserDTO.getUserLoginName());
        user.setUserName(registerUserDTO.getUserName());
        user.setUserPassword(registerUserDTO.getUserPassword());
        user.setUserStatus(1);
        user.setUserType(0);
        user.setUserCreateTime(LocalDateTime.now());
        userService.save(user);
        return Result.success();
    }

    @PostMapping("/registerManager")
    @ValidCode("code")
    @ApiOperation(value = "管理员用户注册", notes = "管理员用户注册")
    public Result<?> registerManager(@Validated  @RequestBody RegisterUserDTO registerUserDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserLoginName, registerUserDTO.getUserLoginName());
        User one = userService.getOne(queryWrapper);
        if(one != null) {
            return Result.error("登录名已被注册");
        }
        User user = new User();
        user.setUserLoginName(registerUserDTO.getUserLoginName());
        user.setUserName(registerUserDTO.getUserName());
        user.setUserPassword(registerUserDTO.getUserPassword());
        user.setUserStatus(1);
        user.setUserType(1);
        user.setUserCreateTime(LocalDateTime.now());
        userService.save(user);
        return Result.success();
    }


    @PostMapping("/updateUser")
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息, 包括用户状态, 用户密码, 用户类型等所有信息(安全性暂不考虑,目前所有信息都可通过接口修改)")
    public Result<?> updateUser(@RequestBody @Validated User user){
        userService.updateById(user);
        return Result.success();
    }
}

