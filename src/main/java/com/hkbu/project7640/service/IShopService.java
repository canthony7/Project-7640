package com.hkbu.project7640.service;

import com.hkbu.project7640.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chet
 * @since 2023-04-09
 */
public interface IShopService extends IService<Shop> {
    List<Shop> getAllShop(String username);

    int deleteShopById(Integer id);

    int addShop(Shop shop);

    Shop getShopByName(String shopName);

    Shop getShopByGoodsId(Integer id);

}
