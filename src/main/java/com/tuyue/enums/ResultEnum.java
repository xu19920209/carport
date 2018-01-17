package com.tuyue.enums;

/**
 * 错误码枚举
 * Created by Administrator on 2017/5/25.
 */
public enum ResultEnum {

    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    NOTNULL(100, "参数不能为空"),
    NOFILENULL(200,"文件不能为空"),
    NOTFOUNDFILE(300,"文件未找到");

    private Integer code;
    private String msg;

    ResultEnum() {
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
