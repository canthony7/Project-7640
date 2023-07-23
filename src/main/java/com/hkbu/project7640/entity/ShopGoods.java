package com.hkbu.project7640.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

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
@TableName("t_shop_goods")
public class ShopGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer shopId;

    private Integer goodsId;


}
