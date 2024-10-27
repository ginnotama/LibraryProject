package com.demo.library.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.library.dto.TypesDTO;
import com.demo.library.pojo.Result;
import com.demo.library.pojo.Type;
import com.demo.library.service.impl.TypeServiceImpl;
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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yogurt
 * @since 2024-10-14
 */
@RestController
@RequestMapping("/type")
@Slf4j
@Api(value = "图书类型模块接口", tags = "图书类型模块接口")
@Transactional(rollbackFor = Exception.class)
public class TypeController {
    @Autowired
    TypeServiceImpl typeService;

    @PostMapping("/types")
    @ApiOperation(value = "图书类型分页查询", notes = "支持传入类型 id, 查询具体类型详情")
    public Result<IPage<Type>> types(@Validated  @RequestBody TypesDTO typesDTO){
        QueryWrapper<Type> queryWrapper = new QueryWrapper<>();
        if(typesDTO.getTypeId() != null){
            queryWrapper.lambda().eq(Type::getTypeId, typesDTO.getTypeId());
        }
        IPage<Type> page = new Page<>(Integer.parseInt(typesDTO.getPageNum()),Integer.parseInt( typesDTO.getPageSize()));
        IPage<Type> typePage = typeService.page(page,queryWrapper);
        return Result.success(typePage);
    }

    @PostMapping("/addType")
    @ApiOperation(value = "新增图书类型", notes = "新增图书类型")
    public Result<?> addType(@Validated  @RequestBody Type type){
        type.setTypeId(null);
        typeService.save(type);
        return Result.success();
    }

    @PostMapping("/updateType")
    @ApiOperation(value = "更新图书类型", notes = "更新图书类型, 通过图书类型 id 标识所需更新数据")
    public Result<?> updateType(@RequestBody Type type){
        if(type.getTypeId() == null){
            return Result.error("当前更新书籍类型不存在, 请核查上传书籍类型 id!");
        }
        typeService.updateById(type);
        return Result.success();
    }

}

