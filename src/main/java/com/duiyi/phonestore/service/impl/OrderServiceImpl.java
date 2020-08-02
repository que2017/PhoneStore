package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.dto.OrderDto;
import com.duiyi.phonestore.entity.OrderMaster;
import com.duiyi.phonestore.entity.PhoneInfo;
import com.duiyi.phonestore.entity.PhoneSpecs;
import com.duiyi.phonestore.enums.PayStatusEnum;
import com.duiyi.phonestore.repository.OrderMasterRepository;
import com.duiyi.phonestore.repository.PhoneInfoRepository;
import com.duiyi.phonestore.repository.PhoneSpecsRepository;
import com.duiyi.phonestore.service.OrderService;
import com.duiyi.phonestore.service.PhoneService;
import com.duiyi.phonestore.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private PhoneService phoneService;

    @Override
    public OrderDto create(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);

        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(orderDto.getSpecsId()).get();
        BeanUtils.copyProperties(phoneSpecs, orderMaster);

        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        BeanUtils.copyProperties(phoneInfo, orderMaster);

        // 计算总价
        BigDecimal orderAmount = new BigDecimal(0);
        orderAmount = phoneSpecs.getSpecsPrice()
                .divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDto.getPhoneQuantity()))
                .add(orderAmount);

        // orderId
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        // 初始化支付状态为未支付
        orderMaster.setPayStatus(PayStatusEnum.UNPAYED.getCode());

        orderMasterRepository.save(orderMaster);

        // 修改库存
        phoneService.subStock(orderDto.getSpecsId(), orderDto.getPhoneQuantity());
        return orderDto;
    }
}
