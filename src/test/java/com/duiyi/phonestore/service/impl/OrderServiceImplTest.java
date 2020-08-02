package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.dto.OrderDto;
import com.duiyi.phonestore.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("张三");
        orderDto.setBuyerPhone("13678787878");
        orderDto.setBuyerAddress("广东省深圳市罗湖区科技路123号456室");
        orderDto.setSpecsId(1);
        orderDto.setPhoneQuantity(1);

        OrderDto result = orderService.create(orderDto);
        System.out.println(result);
    }
}