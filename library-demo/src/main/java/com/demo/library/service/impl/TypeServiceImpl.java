package com.demo.library.service.impl;

import com.demo.library.pojo.Type;
import com.demo.library.dao.TypeDao;
import com.demo.library.service.ITypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeDao, Type> implements ITypeService {

}
