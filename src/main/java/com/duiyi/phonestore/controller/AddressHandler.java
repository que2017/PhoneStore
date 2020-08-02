package com.duiyi.phonestore.controller;

import com.duiyi.phonestore.exception.PhoneException;
import com.duiyi.phonestore.form.AddressForm;
import com.duiyi.phonestore.service.AddressService;
import com.duiyi.phonestore.utils.ResultVoUtil;
import com.duiyi.phonestore.viewobject.AddressVo;
import com.duiyi.phonestore.viewobject.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressHandler {
    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResultVo<List<AddressVo>> list() {
        return ResultVoUtil.success(addressService.findAll());
    }

    @PostMapping("/create")
    public ResultVo<Object> create(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【添加地址】：参数错误，addressForm = {}", addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }

        addressService.saveOrUpdate(addressForm);
        return ResultVoUtil.success(null);
    }

    @PutMapping("/update")
    public ResultVo<Object> update(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【修改地址】：参数错误，addressForm = {}", addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }

        addressService.saveOrUpdate(addressForm);
        return ResultVoUtil.success(null);
    }
}
