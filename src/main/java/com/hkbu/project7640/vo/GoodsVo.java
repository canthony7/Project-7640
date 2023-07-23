package com.hkbu.project7640.vo;

import com.hkbu.project7640.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Chet
 * @date 13/4/2023 7:12 pm
 * @description vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVo extends Goods {
    private Integer shopId;
}
