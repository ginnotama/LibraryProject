package com.demo.library.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yogurt
 * @since 2024-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
@Valid
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    @TableId(value = "type_id", type = IdType.AUTO)
    @ApiModelProperty(value = "类型id", required = false)
    private Long typeId;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称", required = true)
    @NotNull( message = "类型名称 不可为空")
    private String typeName;

    /**
     * 类型描述
     */
    @ApiModelProperty(value = "类型描述", required = true)
    @NotNull( message = "类型描述 不可为空")
    private String typeDesc;


}
