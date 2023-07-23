package com.hkbu.project7640.controller;

import com.hkbu.project7640.dto.ResponseBean;
import com.hkbu.project7640.dto.ResponseEnum;
import com.hkbu.project7640.entity.Goods;
import com.hkbu.project7640.exception.GlobalException;
import com.hkbu.project7640.service.IGoodsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chet
 * @since 2023-04-09
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/project7640")
public class GoodsController {

    @Resource
    IGoodsService goodsService;

    // 查询所有商品
    @GetMapping("/goods")
    public ResponseBean getAllGoods(){
        List<Goods> goods = goodsService.getAllGoods();
        return ResponseBean.success(goods);
    }

    // 根据商店id查询商品
    @GetMapping("/goods/{id}")
    public ResponseBean getGoodsByShopId(@PathVariable @NotNull Integer id){
        List<Goods> goods = goodsService.getGoodsByShopId(id);
        return ResponseBean.success(goods);
    }

    // 根据关键词查询商品
    @GetMapping("/goods/getGoodsByKeyword/{keyword}")
    public ResponseBean getGoodsByKeyword(@PathVariable @NotNull String keyword){
        List<Goods> goods = goodsService.getGoodsByKeyword(keyword);
        return ResponseBean.success(goods);
    }

    // 商户添加商品, 商户权限
    @RequiresRoles(value = "merchant")
    @PostMapping("/addGoods")
    // goodName, price, keyword
    public ResponseBean addGoods(@RequestBody Goods goods){
        int i = goodsService.addGoods(goods);
        if (i == 0){
            throw new GlobalException(ResponseEnum.ADD_GOODS_ERROR);
        }
        return ResponseBean.success();
    }

}
