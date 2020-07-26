package com.duiyi.phonestore.repository;

import com.duiyi.phonestore.entity.PhoneCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneCategoryRepository extends JpaRepository<PhoneCategory, Integer> {
    public PhoneCategory findByCategoryId(Integer categoryType);
}
