package com.hkbu.project7640.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.hkbu.project7640.entity.Goods;
import com.hkbu.project7640.entity.Shop;
import com.hkbu.project7640.entity.ShopGoods;
import com.hkbu.project7640.entity.User;
import com.hkbu.project7640.mapper.GoodsMapper;
import com.hkbu.project7640.mapper.ShopGoodsMapper;
import com.hkbu.project7640.mapper.ShopMapper;
import com.hkbu.project7640.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hkbu.project7640.service.IUserService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    GoodsMapper goodsMapper;

    @Resource
    ShopGoodsMapper shopGoodsMapper;

    @Override
    public List<Goods> getGoodsByShopId(Integer id) {
        QueryWrapper<ShopGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("shop_id", id);
        List<ShopGoods> shopGoods = shopGoodsMapper.selectList(wrapper);
        List<Integer> goodsIds = new ArrayList<>();
        for (ShopGoods shopGood : shopGoods) {
            goodsIds.add(shopGood.getGoodsId());
        }
        List<Goods> goods = goodsMapper.selectBatchIds(goodsIds);
        return goods;
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsMapper.selectList(null);
    }

    @Override
    public List<Goods> getGoodsByKeyword(String keyword) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("good_name", keyword).or().like("keyword", keyword);
        return goodsMapper.selectList(wrapper);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public int addGoods(Goods goods) {
        // 如果商品存在，则返回错误
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("good_name", goods.getGoodName());
        Goods goods1 = goodsMapper.selectOne(wrapper);
        if (goods1 != null){
            return 0;
        }
        User user = UserHolder.getUser();
        // 插入数据到商品表
        goodsMapper.insert(goods);

        // 插入数据到商店商品关系表
        ShopGoods shopGoods = new ShopGoods();
        shopGoods.setGoodsId(goods.getId());
        shopGoods.setShopId(user.getShopId());
        shopGoodsMapper.insert(shopGoods);
        return 1;
    }
}
