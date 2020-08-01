package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.service.PhoneService;
import com.duiyi.phonestore.viewobject.DataVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneServiceImplTest {
    @Autowired
    private PhoneService phoneService;

    @Test
    void findDataVo() {
        DataVo dataVo = phoneService.findDataVo();
    }
}