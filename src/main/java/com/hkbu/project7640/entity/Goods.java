package com.hkbu.project7640.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author chet
 * @since 2023-04-09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "商品名称不能为空！")
    private String goodName;

    @NotNull(message = "价格不能为空！")
    private BigDecimal price;

    @NotNull(message = "关键词不能为空！")
    private String keyword;


}
