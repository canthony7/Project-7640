package com.hkbu.project7640.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.validation.constraints.NotNull;
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
@TableName("t_shop")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotNull(message = "商店名不能为空！")
    private String shopName;

    private Integer rating;

    @NotNull(message = "地址不能为空！")
    private String location;


}
