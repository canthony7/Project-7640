package com.hkbu.project7640;

import com.alibaba.fastjson2.JSON;
import com.hkbu.project7640.dto.ResponseEnum;
import com.hkbu.project7640.entity.*;
import com.hkbu.project7640.exception.GlobalException;
import com.hkbu.project7640.mapper.UserMapper;
import com.hkbu.project7640.service.IGoodsService;
import com.hkbu.project7640.service.IOrderService;
import com.hkbu.project7640.service.IShopService;
import com.hkbu.project7640.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Project7640ApplicationTests {

    @Resource
    IGoodsService goodsService;

    @Resource
    IShopService shopService;

    @Resource
    UserMapper userMapper;

    @Resource
    JWTUtils jwtUtils;

    @Resource
    Hello hello;

    @Test
    void contextLoads() {
        List<Goods> goods = goodsService.getGoodsByKeyword("Blue");
        for (Goods good : goods) {
            System.out.println(good.toString());
        }
    }

    @Test
    void test02(){
        Shop shop = shopService.getShopByGoodsId(4);
        System.out.println(shop);
    }

    @Test
    void test03(){
        hello.sayHello();

    }
}
