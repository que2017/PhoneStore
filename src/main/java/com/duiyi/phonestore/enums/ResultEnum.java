package com.duiyi.phonestore.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PHONE_STOCK_ERROR(0, "手机库存不足"),
    ORDER_NOT_EXIST(1, "订单不存在");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
