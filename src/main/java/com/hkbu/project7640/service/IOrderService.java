package com.hkbu.project7640.service;

import com.hkbu.project7640.dto.ResponseBean;
import com.hkbu.project7640.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hkbu.project7640.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chet
 * @since 2023-04-09
 */
public interface IOrderService extends IService<Order> {

    ResponseBean createOrder(List<Integer> itemIds);

    Map<String, List<Order>> getAllOrder(User user);

    List<String> getOrderCodeByUser(User user);

    List<Order> getAllOrderByUser();

    int cancelOrderById(Integer id);

}
