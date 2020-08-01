package com.duiyi.phonestore.viewobject;

import lombok.Data;

import java.util.List;

@Data
public class TreeVo {
    private String k = "规格";
    private List<PhoneSpecsVo> v;
    private String k_s = "s1";
}
