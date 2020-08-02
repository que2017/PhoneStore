package com.duiyi.phonestore.controller;

import com.duiyi.phonestore.dto.OrderDto;
import com.duiyi.phonestore.exception.PhoneException;
import com.duiyi.phonestore.form.OrderForm;
import com.duiyi.phonestore.service.OrderService;
import com.duiyi.phonestore.utils.ResultVoUtil;
import com.duiyi.phonestore.viewobject.OrderDetailVo;
import com.duiyi.phonestore.viewobject.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderHandler {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】：参数错误，orderForm = {}", orderForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }

        // 把orderForm转成orderDto
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getTel());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setSpecsId(orderForm.getSpecsId());
        orderDto.setPhoneQuantity(orderForm.getQuantity());

        OrderDto result = orderService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());

        return ResultVoUtil.success(map);
    }

    @GetMapping("/detail/{orderId}")
    public ResultVo<OrderDetailVo> findOrderDetail(@PathVariable String orderId) {
        return ResultVoUtil.success(orderService.findOrderDetailByOrderId(orderId));
    }

    @PutMapping("/pay/{orderId}")
    public ResultVo<Map<String, String>> pay(@PathVariable String orderId) {
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderService.pay(orderId));
        return ResultVoUtil.success(map);
    }
}
