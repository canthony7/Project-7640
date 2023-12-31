package com.hkbu.project7640.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Chet
 * @date 10/2/2023 3:04 am
 */
@AllArgsConstructor
@Getter
@ToString
public enum ResponseEnum {
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),
    DELETE_ERROR(40001, "删除失败"),

    ADD_SHOP_ERROR(40002, "添加商店失败"),

    LOGIN_ERROR(40003, "登录失败"),

    TOKEN_ERROR(40004, "token_expire"),

    EMPTY_TOKEN(40005, "token为空"),

    NOT_EXIST_ERROR(40006, "用户不存在"),

    ADD_GOODS_ERROR(40007, "添加商品失败"),

    CANCEL_ORDER_ERROR(40008, "订单状态为已取消"),
    ORDER_ERROR(40009, "下单错误");

    private final Integer code;
    private final String message;

}
