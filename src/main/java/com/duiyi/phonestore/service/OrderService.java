package com.duiyi.phonestore.service;

import com.duiyi.phonestore.dto.OrderDto;
import com.duiyi.phonestore.viewobject.OrderDetailVo;

public interface OrderService {
    OrderDto create(OrderDto orderDto);
    OrderDetailVo findOrderDetailByOrderId(String orderId);
    String pay(String orderId);
}
