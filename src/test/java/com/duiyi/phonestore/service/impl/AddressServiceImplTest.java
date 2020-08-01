package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.form.AddressForm;
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

    @Test
    void saveOrUpdate() {
        AddressForm addressForm = new AddressForm();
        addressForm.setId(4);
        addressForm.setName("张三");
        addressForm.setTel("13612344321");
        addressForm.setProvince("北京市");
        addressForm.setCity("北京市");
        addressForm.setCountry("东城区");
        addressForm.setAreaCode("110101");
        addressForm.setAddressDetail("168号606室");
        addressService.saveOrUpdate(addressForm);
    }
}