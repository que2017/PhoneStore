package com.duiyi.phonestore.exception;

import com.duiyi.phonestore.enums.ResultEnum;

public class PhoneException extends RuntimeException {
    public PhoneException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
