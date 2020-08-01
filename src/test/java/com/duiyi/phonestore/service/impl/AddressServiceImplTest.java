package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.service.AddressService;
import com.duiyi.phonestore.viewobject.AddressVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {
    @Autowired
    private AddressService addressService;

    @Test
    void findAll() {
        List<AddressVo> addressVoList = addressService.findAll();
        addressVoList.forEach(System.out::println);
    }
}