package com.hongcheng.fruitmall.common.constants;

public enum  RestResponseCode {

    SUCCESS(200, "success"),
    CUSTOMER_ERROR(400, "customer error"),
    SERVER_ERROR(500, "server error");

    private Integer code;

    private String msg;

    RestResponseCode(Integer code, String msg) {
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
