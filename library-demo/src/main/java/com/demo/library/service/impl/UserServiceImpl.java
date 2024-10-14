package com.demo.library.service.impl;

import com.demo.library.pojo.User;
import com.demo.library.dao.UserDao;
import com.demo.library.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

}
