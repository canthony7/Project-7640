package com.hkbu.project7640.controller;


import com.hkbu.project7640.dto.ResponseBean;
import com.hkbu.project7640.dto.ResponseEnum;
import com.hkbu.project7640.entity.Order;
import com.hkbu.project7640.exception.GlobalException;
import com.hkbu.project7640.service.IOrderService;
import com.hkbu.project7640.utils.UserHolder;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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
public class OrderController {

    @Resource
    IOrderService orderService;

    // 下单接口
    @RequiresRoles(value = {"admin", "merchant", "user"}, logical = Logical.OR)
    @PostMapping("/orders")
    public ResponseBean createOrder(@RequestBody @NotNull List<Integer> itemIds){
        return orderService.createOrder(itemIds);
    }

    // 获取订单接口
    @RequiresRoles(value = {"admin", "merchant", "user"}, logical = Logical.OR)
    @GetMapping("/orders")
    public ResponseBean getAllOrder(){
        Map<String, List<Order>> orders = orderService.getAllOrder(UserHolder.getUser());
        return ResponseBean.success();
    }

    // 查询订单接口
    @RequiresRoles(value = {"admin", "merchant", "user"}, logical = Logical.OR)
    @GetMapping("/allOrders")
    public ResponseBean getAllOrderByUser(){
        List<Order> allOrder = orderService.getAllOrderByUser();
        return ResponseBean.success(allOrder);
    }

    // 取消订单接口
    @RequiresRoles(value = {"admin", "merchant", "user"}, logical = Logical.OR)
    @PutMapping("/orders/{id}")
    public ResponseBean cancelOrder(@PathVariable Integer id){
        int i = orderService.cancelOrderById(id);
        if (i == 0){
            throw new GlobalException(ResponseEnum.CANCEL_ORDER_ERROR);
        }
        return ResponseBean.success();
    }
}
