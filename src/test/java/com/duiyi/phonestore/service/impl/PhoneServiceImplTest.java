package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.service.PhoneService;
import com.duiyi.phonestore.viewobject.DataVo;
import com.duiyi.phonestore.viewobject.PhoneInfoVo;
import com.duiyi.phonestore.viewobject.SpecsPackageVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneServiceImplTest {
    @Autowired
    private PhoneService phoneService;

    @Test
    void findDataVo() {
        DataVo dataVo = phoneService.findDataVo();
        System.out.println(dataVo);
    }

    @Test
    void findPhoneInfoVoByCategoryType() {
        List<PhoneInfoVo> phoneInfoVos = phoneService.findPhoneInfoVoByCategoryType(2);
        phoneInfoVos.forEach(System.out::println);
    }

    @Test
    void findSku() {
        SpecsPackageVo specsPackageVo = phoneService.findSpecsByPhoneId(1);
        System.out.println(specsPackageVo);
    }

    @Test
    void subStock() {
        phoneService.subStock(1, 2);
    }
}