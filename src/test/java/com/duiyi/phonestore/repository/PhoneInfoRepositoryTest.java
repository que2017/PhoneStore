package com.duiyi.phonestore.repository;

import com.duiyi.phonestore.entity.PhoneInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhoneInfoRepositoryTest {
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Test
    void findAll() {
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAll();
        for (PhoneInfo phoneInfo : phoneInfoList) {
            System.out.println(phoneInfo);
        }
    }

    @Test
    void findAllByCategoryType() {
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(1);
        for (PhoneInfo phoneInfo : phoneInfoList) {
            System.out.println(phoneInfo);
        }
    }
}