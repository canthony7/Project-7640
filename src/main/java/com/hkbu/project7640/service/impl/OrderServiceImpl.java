package com.hkbu.project7640.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hkbu.project7640.dto.ResponseBean;
import com.hkbu.project7640.dto.ResponseEnum;
import com.hkbu.project7640.entity.Goods;
import com.hkbu.project7640.entity.Order;
import com.hkbu.project7640.entity.Shop;
import com.hkbu.project7640.entity.User;
import com.hkbu.project7640.mapper.OrderMapper;
import com.hkbu.project7640.service.IGoodsService;
import com.hkbu.project7640.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hkbu.project7640.service.IShopService;
import com.hkbu.project7640.utils.OrderUtils;
import com.hkbu.project7640.utils.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chet
 * @since 2023-04-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    IGoodsService goodsService;

    @Resource
    IShopService shopService;

    @Resource
    OrderMapper orderMapper;

    @Override
    public ResponseBean createOrder(List<Integer> itemIds) {
        if (itemIds == null){
            return ResponseBean.fail(ResponseEnum.FAIL);
        }
        Integer userId = UserHolder.getUser().getId();
        String orderCode = OrderUtils.getOrderCode(userId);
        // 遍历每一个商品id
        for (Integer itemId : itemIds) {
            // userId, orderCode, price, shopId, createTime, status, goodId, goodName
            Goods goods = goodsService.getGoodsById(itemId);
            Shop shop = shopService.getShopByGoodsId(itemId);
            // 生成订单
            Order order = new Order();
            order.setOrderId(orderCode);
            order.setPrice(goods.getPrice());
            order.setStatus("submitted");
            order.setGoodId(goods.getId());
            order.setUserId(userId);
            order.setGoodName(goods.getGoodName());
            order.setShopId(shop.getId());
            // 插入数据库
            int insert = orderMapper.insert(order);
            if (insert == 0){
                return ResponseBean.fail(ResponseEnum.ORDER_ERROR);
            }
        }
        return ResponseBean.success();
    }

    @Override
    public Map<String, List<Order>> getAllOrder(User user) {
        List<String> orderIds = getOrderCodeByUser(user);
        Map<String, List<Order>> listMap = new HashMap<>();
        orderIds.forEach(orderId -> {
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_id", orderId);
            List<Order> orders = orderMapper.selectList(wrapper);
            if (orders.size() > 0) {
                listMap.put(orderId, orders);
            }
        });
        return listMap;
    }

    public List<String> getOrderCodeByUser(User user) {
        QueryWrapper<Order> wrapper = new QueryWrapper();
        wrapper.eq("user_id", user.getId());
        wrapper.groupBy("order_id");
        wrapper.select("order_id");
        List<Order> list = orderMapper.selectList(wrapper);
        Iterator<Order> iterator = list.iterator();
        List<String> orderIds = new ArrayList<>();
        while (iterator.hasNext()){
            orderIds.add(iterator.next().getOrderId());
        }
        return orderIds;
    }

    @Override
    public List<Order> getAllOrderByUser() {
        User user = UserHolder.getUser();
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        // 根据user的id查询
        wrapper.eq("user_id", user.getId());
        return orderMapper.selectList(wrapper);
    }

    @Override
    public int cancelOrderById(Integer id) {
        Order order = orderMapper.selectById(id);
        String status = order.getStatus();
        // 如果状态为canceled则无法取消
        if (status.equals("canceled")){
            return 0;
        } else {
            // 否则更新订单状态
            order.setStatus("canceled");
            orderMapper.updateById(order);
            return 1;
        }
    }
}
