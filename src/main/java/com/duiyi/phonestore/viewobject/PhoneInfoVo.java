package com.duiyi.phonestore.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneInfoVo {
    @JsonProperty("id")
    private Integer phoneId;
    @JsonProperty("title")
    private String phoneName;
    @JsonProperty("price")
    private BigDecimal phonePrice;
    @JsonProperty("desc")
    private String phoneDescription;
    @JsonProperty("tag")
    private List<Map<String ,String>> tag;
    @JsonProperty("thumb")
    private String phoneIcon;
}
