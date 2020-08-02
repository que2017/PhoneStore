package com.duiyi.phonestore.utils;

import com.duiyi.phonestore.viewobject.ResultVo;

public class ResultVoUtil {
    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo error(String error) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1);
        resultVo.setMsg(error);
        return resultVo;
    }

    private ResultVoUtil() {
    }
}
