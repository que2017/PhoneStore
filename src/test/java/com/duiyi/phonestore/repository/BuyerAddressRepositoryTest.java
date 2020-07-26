package com.duiyi.phonestore.repository;

import com.duiyi.phonestore.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BuyerAddressRepositoryTest {
    @Autowired
    private BuyerAddressRepository repository;

    @Test
    void finaAll() {
        List<BuyerAddress> list = repository.findAll();
        for (BuyerAddress buyerAddress : list) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    void save() {
        BuyerAddress address = new BuyerAddress();
        address.setAreaCode("110101");
        address.setBuyerAddress("北京市东城区168号306室");
        address.setBuyerName("张三");
        address.setBuyerPhone("13678900987");
        repository.save(address);
    }

    @Test
    void update() {
        BuyerAddress address = repository.findById(3).get();
        address.setBuyerName("李四");
        repository.save(address);
    }
}