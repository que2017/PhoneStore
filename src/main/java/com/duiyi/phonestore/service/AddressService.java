package com.duiyi.phonestore.service;

import com.duiyi.phonestore.form.AddressForm;
import com.duiyi.phonestore.viewobject.AddressVo;

import java.util.List;

public interface AddressService {
    List<AddressVo> findAll();
    void saveOrUpdate(AddressForm addressForm);
}
