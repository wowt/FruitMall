package com.hongcheng.fruitmall.common.constants;

import static com.hongcheng.fruitmall.common.constants.RestResponseCode.SUCCESS;

/**
 * rest接口标准响应对象
 * @author wanghongcheng
 *
 * @param <T>
 */
public class RestResponse<T> {

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息，错误时为错误说明
     */
    private String msg;

    /**
     * 响应结果
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    private RestResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <R> RestResponse<R> success(R data) {
        return new RestResponse<>(SUCCESS.getCode(), SUCCESS.getMsg(), data);
    }

    public static <R> RestResponse<R> error(RestResponseCode errorCode) {
        return error(errorCode, errorCode.getMsg());
    }

    public static <R> RestResponse<R> error(RestResponseCode errorCode, String message) {
        return error(errorCode, message, null);
    }

    public static <R> RestResponse<R> error(RestResponseCode errorCode, String message, R data) {
        return new RestResponse<>(errorCode.getCode(), message, data);
    }
}
