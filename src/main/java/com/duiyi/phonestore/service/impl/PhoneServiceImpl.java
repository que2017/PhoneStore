package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.entity.PhoneCategory;
import com.duiyi.phonestore.entity.PhoneInfo;
import com.duiyi.phonestore.entity.PhoneSpecs;
import com.duiyi.phonestore.repository.PhoneCategoryRepository;
import com.duiyi.phonestore.repository.PhoneInfoRepository;
import com.duiyi.phonestore.repository.PhoneSpecsRepository;
import com.duiyi.phonestore.service.PhoneService;
import com.duiyi.phonestore.utils.PhoneUtil;
import com.duiyi.phonestore.viewobject.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Override
    public DataVo findDataVo() {
        DataVo dataVo = new DataVo();
        // 获取类型
        List<PhoneCategory> phoneCategories = phoneCategoryRepository.findAll();
//        List<PhoneCategoryVo> phoneCategoryVos = new ArrayList<>();
//        for (PhoneCategory phoneCategory : phoneCategories) {
//            PhoneCategoryVo phoneCategoryVo = new PhoneCategoryVo();
//            phoneCategoryVo.setCategoryName(phoneCategory.getCategoryName());
//            phoneCategoryVo.setCategoryType(phoneCategory.getCategoryType());
//            phoneCategoryVos.add(phoneCategoryVo);
//        }
        List<PhoneCategoryVo> phoneCategoryVos = phoneCategories.stream()
                .map(item -> new PhoneCategoryVo(item.getCategoryName(), item.getCategoryType()))
                .collect(Collectors.toList());
        dataVo.setCategories(phoneCategoryVos);
        // 获取手机
        List<PhoneInfoVo> phoneInfoVos = findPhoneInfoVoByCategoryType(phoneCategories.get(0).getCategoryType());
        dataVo.setPhones(phoneInfoVos);
        return dataVo;
    }

    @Override
    public List<PhoneInfoVo> findPhoneInfoVoByCategoryType(Integer categoryType) {
        List<PhoneInfo> phoneInfos = phoneInfoRepository.findAllByCategoryType(categoryType);

//        List<PhoneInfoVo> phoneInfoVos = new ArrayList<>();
//        for (PhoneInfo phoneInfo : phoneInfos) {
//            PhoneInfoVo phoneInfoVo = new PhoneInfoVo();
//            BeanUtils.copyProperties(phoneInfo, phoneInfoVo);
//            phoneInfoVo.setTag(PhoneUtil.createTag(phoneInfo.getPhoneTag()));
//            phoneInfoVos.add(phoneInfoVo);
//        }
        return phoneInfos.stream().map(item -> {
            PhoneInfoVo phoneInfoVo = new PhoneInfoVo();
            BeanUtils.copyProperties(item, phoneInfoVo);
            phoneInfoVo.setTag(PhoneUtil.createTag(item.getPhoneTag()));
            return phoneInfoVo;
        }).collect(Collectors.toList());
    }

    @Override
    public SpecsPackageVo findSpecsByPhoneId(Integer phoneId) {
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecsList = phoneSpecsRepository.findAllByPhoneId(phoneId);

        // 获取tree
        List<PhoneSpecsVo> phoneSpecsVoList = new ArrayList<>();
        List<PhoneSpecsCasVo> phoneSpecsCasVoList = new ArrayList<>();
        PhoneSpecsVo phoneSpecsVo;
        PhoneSpecsCasVo phoneSpecsCasVo;
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {
            phoneSpecsVo = new PhoneSpecsVo();
            phoneSpecsCasVo = new PhoneSpecsCasVo();
            BeanUtils.copyProperties(phoneSpecs, phoneSpecsVo);
            BeanUtils.copyProperties(phoneSpecs, phoneSpecsCasVo);
            phoneSpecsVoList.add(phoneSpecsVo);
            phoneSpecsCasVoList.add(phoneSpecsCasVo);
        }
        TreeVo treeVo = new TreeVo();
        treeVo.setV(phoneSpecsVoList);
        List<TreeVo> treeVoList = new ArrayList<>();
        treeVoList.add(treeVo);

        SkuVo skuVo = new SkuVo();
        Integer price = phoneInfo.getPhonePrice().intValue();
        skuVo.setPrice(price + ".00");
        skuVo.setStock_num(phoneInfo.getPhoneStock());
        skuVo.setTree(treeVoList);
        skuVo.setList(phoneSpecsCasVoList);

        SpecsPackageVo specsPackageVo = new SpecsPackageVo();
        specsPackageVo.setSku(skuVo);
        Map<String, String> goods = new HashMap<>();
        goods.put("picture", phoneInfo.getPhoneIcon());
        specsPackageVo.setGoods(goods);

        return specsPackageVo;
    }
}
