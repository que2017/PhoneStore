package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.entity.BuyerAddress;
import com.duiyi.phonestore.form.AddressForm;
import com.duiyi.phonestore.repository.BuyerAddressRepository;
import com.duiyi.phonestore.service.AddressService;
import com.duiyi.phonestore.viewobject.AddressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private BuyerAddressRepository buyerAddressRepository;

    @Override
    public List<AddressVo> findAll() {
        List<BuyerAddress> buyerAddressList = buyerAddressRepository.findAll();
        return buyerAddressList.stream().map(item -> new AddressVo(
                item.getAddressId(),
                item.getAreaCode(),
                item.getBuyerName(),
                item.getBuyerPhone(),
                item.getBuyerAddress()
        )).collect(Collectors.toList());
    }

    @Override
    public void saveOrUpdate(AddressForm addressForm) {
        BuyerAddress buyerAddress;
        // 如果addressForm的id没有值，代表添加
        if (addressForm.getId() == null) {
            buyerAddress = new BuyerAddress();
        } else {
            buyerAddress = buyerAddressRepository.findById(addressForm.getId()).get();
        }
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(addressForm.getProvince())
                .append(addressForm.getCity())
                .append(addressForm.getCountry())
                .append(addressForm.getAddressDetail());
        buyerAddress.setBuyerAddress(stringBuilder.toString());
        buyerAddress.setAreaCode(addressForm.getAreaCode());
        buyerAddressRepository.save(buyerAddress);
    }
}
