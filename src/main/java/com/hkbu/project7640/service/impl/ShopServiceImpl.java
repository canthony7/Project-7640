package com.hkbu.project7640.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hkbu.project7640.entity.Shop;
import com.hkbu.project7640.entity.ShopGoods;
import com.hkbu.project7640.entity.User;
import com.hkbu.project7640.mapper.ShopGoodsMapper;
import com.hkbu.project7640.mapper.ShopMapper;
import com.hkbu.project7640.mapper.UserMapper;
import com.hkbu.project7640.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hkbu.project7640.utils.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chet
 * @since 2023-04-09
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    ShopMapper shopMapper;

    @Resource
    ShopGoodsMapper shopGoodsMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public List<Shop> getAllShop(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("telephone", username);
        User user = userMapper.selectOne(wrapper);
        // 如果是user，就返回所有的shop。
        if (user.getShopId() == null){
            return shopMapper.selectList(null);
        } else {
            // 如果是merchant，就返回他的shop
            QueryWrapper<Shop> shopQueryWrapper = new QueryWrapper<>();
            shopQueryWrapper.eq("id", user.getShopId());
            return shopMapper.selectList(shopQueryWrapper);
        }
    }

    // 删除商店
    @Override
    public int deleteShopById(Integer id) {
        return shopMapper.deleteById(id);
    }

    // 添加商店
    @Override
    public int addShop(Shop shop) {
        return shopMapper.insert(shop);
    }

    // 通过名称得到商店
    @Override
    public Shop getShopByName(String shopName) {
        QueryWrapper<Shop> wrapper = new QueryWrapper<>();
        wrapper.eq("shop_name", shopName);
        return shopMapper.selectOne(wrapper);
    }

    @Override
    public Shop getShopByGoodsId(Integer id) {
        QueryWrapper<ShopGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", id);
        ShopGoods shopGoods = shopGoodsMapper.selectOne(wrapper);
        Shop shop = shopMapper.selectById(shopGoods.getShopId());
        return shop;
    }

}
