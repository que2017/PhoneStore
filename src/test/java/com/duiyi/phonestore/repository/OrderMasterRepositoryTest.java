package com.duiyi.phonestore.repository;

import com.duiyi.phonestore.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;

    @Test
    void findAll() {
        List<OrderMaster> orderMasters = repository.findAll();
        for (OrderMaster orderMaster : orderMasters) {
            System.out.println(orderMaster);
        }
    }

    @Test
    void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("abcsh3213");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerAddress("上海");
        orderMaster.setBuyerPhone("15625366473");
        orderMaster.setOrderAmount(new BigDecimal(6400));
        orderMaster.setPhoneIcon("/a/d/c.jpg");
        orderMaster.setPhoneId(1);
        orderMaster.setPhoneName("P40 pro plus");
        orderMaster.setPhoneQuantity(2);
        orderMaster.setSpecsId(1);
        orderMaster.setSpecsName("128GB");
        orderMaster.setSpecsPrice(new BigDecimal(879900));
        repository.save(orderMaster);
    }

    @Test
    void findById() {
        OrderMaster orderMaster = repository.findById("abcsh3213").get();
        System.out.println(orderMaster);
    }

    @Test
    void pay() {
        OrderMaster orderMaster = repository.findById("abcsh3213").get();
        orderMaster.setPayStatus(1);
        repository.save(orderMaster);
    }
}