package com.hkbu.project7640.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderId;

    private Integer userId;

    private BigDecimal price;

    private Integer shopId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private String status;

    private Integer goodId;

    private String goodName;


}
