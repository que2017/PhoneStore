package com.duiyi.phonestore.service;

import com.duiyi.phonestore.viewobject.DataVo;
import com.duiyi.phonestore.viewobject.PhoneInfoVo;

import java.util.List;

public interface PhoneService {
    DataVo findDataVo();
    List<PhoneInfoVo> findPhoneInfoVoByCategoryType(Integer categoryType);
}
