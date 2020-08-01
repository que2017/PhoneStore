package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.entity.BuyerAddress;
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
}
