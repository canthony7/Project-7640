package com.hkbu.project7640.controller;


import com.hkbu.project7640.dto.ResponseBean;
import com.hkbu.project7640.dto.ResponseEnum;
import com.hkbu.project7640.entity.Shop;
import com.hkbu.project7640.exception.GlobalException;
import com.hkbu.project7640.service.IShopService;
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
public class ShopController {

    @Resource
    IShopService shopService;

    // 主页得到所有的shop
    @GetMapping("/shops/{username}")
    public ResponseBean getAllShop(@PathVariable String username){
        List<Shop> shops = shopService.getAllShop(username);
        return ResponseBean.success(shops);
    }

    // 管理员删除shop
    @RequiresRoles(value = "admin")
    @DeleteMapping("/shop/{id}")
    public ResponseBean deleteShop(@PathVariable @NotNull Integer id){
        int i = shopService.deleteShopById(id);
        if (i == 0){
            throw new GlobalException(ResponseEnum.DELETE_ERROR);
        }
        return ResponseBean.success();
    }

    // 管理员添加shop
    @RequiresRoles(value = "admin")
    @PostMapping("/shop")
    public ResponseBean addShop(@RequestBody Shop shop){
        // 参数为空则返回错误
        if (shop.getShopName() == null || shop.getLocation() == null){
            throw new GlobalException(ResponseEnum.ADD_SHOP_ERROR);
        }
        // 如果shop存在则返回错误
        if (shopService.getShopByName(shop.getShopName())!=null){
            throw new GlobalException(ResponseEnum.ADD_SHOP_ERROR);
        }
        int i = shopService.addShop(shop);
        return ResponseBean.success();
    }
}
