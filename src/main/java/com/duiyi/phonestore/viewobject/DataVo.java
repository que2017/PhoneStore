package com.duiyi.phonestore.viewobject;

import lombok.Data;

import java.util.List;

@Data
public class DataVo {
    private List<PhoneCategoryVo> categories;
    private List<PhoneInfoVo> phones;
}
