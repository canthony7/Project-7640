package com.hkbu.project7640.service;

import com.hkbu.project7640.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hkbu.project7640.entity.Shop;
import com.hkbu.project7640.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chet
 * @since 2023-04-09
 */
public interface IGoodsService extends IService<Goods> {

    List<Goods> getGoodsByShopId(Integer id);

    List<Goods> getAllGoods();

    List<Goods> getGoodsByKeyword(String keyword);

    Goods getGoodsById(Integer id);

    int addGoods(Goods goods);

}
