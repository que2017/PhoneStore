package com.duiyi.phonestore.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneUtil {
    public static List<Map<String, String>> createTag(String tag) {
        String[] tags = tag.split("&");
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map;
        for (String str : tags) {
            map = new HashMap<>();
            map.put("name", str);
            list.add(map);
        }
        return list;
    }

    private PhoneUtil() {
    }
}
