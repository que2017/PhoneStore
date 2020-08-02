package com.duiyi.phonestore.controller;

import com.duiyi.phonestore.service.PhoneService;
import com.duiyi.phonestore.utils.ResultVoUtil;
import com.duiyi.phonestore.viewobject.DataVo;
import com.duiyi.phonestore.viewobject.PhoneInfoVo;
import com.duiyi.phonestore.viewobject.ResultVo;
import com.duiyi.phonestore.viewobject.SpecsPackageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @GetMapping("/index")
    public ResultVo<DataVo> index() {
        return ResultVoUtil.success(phoneService.findDataVo());
    }

    @GetMapping("/findByCategoryType/{categoryType}")
    public ResultVo<List<PhoneInfoVo>> findByCategoryType(@PathVariable("categoryType") Integer categoryType) {
        return ResultVoUtil.success(phoneService.findPhoneInfoVoByCategoryType(categoryType));
    }

    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public ResultVo<SpecsPackageVo> findSpecsByPhoneId(@PathVariable("phoneId") Integer phoneId) {
        return ResultVoUtil.success(phoneService.findSpecsByPhoneId(phoneId));
    }
}
