package com.duiyi.phonestore.repository;

import com.duiyi.phonestore.entity.PhoneCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PhoneCategoryRepositoryTest {
    @Autowired
    private PhoneCategoryRepository repository;

    @Test
    void findAll() {
        List<PhoneCategory> list = repository.findAll();
        for (PhoneCategory phoneCategory : list) {
            System.out.println(phoneCategory);
        }
    }

    @Test
    void findByCategoryType() {
        PhoneCategory phoneCategory = repository.findByCategoryId(2);
        System.out.println(phoneCategory);
    }
}