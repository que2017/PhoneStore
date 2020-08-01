package com.duiyi.phonestore.service;

import com.duiyi.phonestore.viewobject.DataVo;
import com.duiyi.phonestore.viewobject.PhoneInfoVo;
import com.duiyi.phonestore.viewobject.SpecsPackageVo;

import java.util.List;

public interface PhoneService {
    DataVo findDataVo();
    List<PhoneInfoVo> findPhoneInfoVoByCategoryType(Integer categoryType);
    SpecsPackageVo findSpecsByPhoneId(Integer phoneId);
    void subStock(Integer specsId, Integer quantity);
}
