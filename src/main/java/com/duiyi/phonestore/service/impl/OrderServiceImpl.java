package com.duiyi.phonestore.service.impl;

import com.duiyi.phonestore.dto.OrderDto;
import com.duiyi.phonestore.entity.OrderMaster;
import com.duiyi.phonestore.entity.PhoneInfo;
import com.duiyi.phonestore.entity.PhoneSpecs;
import com.duiyi.phonestore.enums.PayStatusEnum;
import com.duiyi.phonestore.enums.ResultEnum;
import com.duiyi.phonestore.exception.PhoneException;
import com.duiyi.phonestore.repository.OrderMasterRepository;
import com.duiyi.phonestore.repository.PhoneInfoRepository;
import com.duiyi.phonestore.repository.PhoneSpecsRepository;
import com.duiyi.phonestore.service.OrderService;
import com.duiyi.phonestore.service.PhoneService;
import com.duiyi.phonestore.utils.KeyUtil;
import com.duiyi.phonestore.viewobject.OrderDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
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
                .add(orderAmount).add(new BigDecimal(10));

        // orderId
        orderMaster.setOrderId(KeyUtil.createUniqueKey());
        // 初始化支付状态为未支付
        orderMaster.setPayStatus(PayStatusEnum.UNPAYED.getCode());

        orderMasterRepository.save(orderMaster);

        // 修改库存
        phoneService.subStock(orderDto.getSpecsId(), orderDto.getPhoneQuantity());
        return orderDto;
    }

    @Override
    public OrderDetailVo findOrderDetailByOrderId(String orderId) {
        OrderDetailVo orderDetailVo = new OrderDetailVo();

        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        BeanUtils.copyProperties(orderMaster, orderDetailVo);
        // 处理OrderDetailVo的specsPrice和OrderMaster的specsPrice类型不同的问题
        orderDetailVo.setSpecsPrice(orderMaster.getSpecsPrice().divide(new BigDecimal(100)) + ".00");

        return orderDetailVo;
    }

    @Override
    public String pay(String orderId) {
        Optional<OrderMaster> orderMasterOpt = orderMasterRepository.findById(orderId);
        if (!orderMasterOpt.isPresent()) {
            log.error("【支付订单】：订单为空，orderId = {}", orderId);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderMaster orderMaster = orderMasterOpt.get();
        if (PayStatusEnum.UNPAYED.getCode().equals(orderMaster.getPayStatus())) {
            orderMaster.setPayStatus(PayStatusEnum.PAYED.getCode());
            orderMasterRepository.save(orderMaster);
        } else {
            log.error("【支付订单】：订单已支付，orderId = {}", orderId);
        }
        return orderId;
    }
}
