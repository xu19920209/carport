package com.tuyue.exception;


import com.tuyue.enums.ResultEnum;

/**
 * 异常处理类
 * Created by Administrator on 2017/5/25.
 */
public class AdminException extends RuntimeException {
    private Integer code;

    public AdminException() {

    }

    public AdminException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }



    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
