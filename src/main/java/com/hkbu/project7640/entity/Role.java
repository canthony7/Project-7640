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
 * @since 2023-04-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    private String roleName;

    private String roleDesc;


}
