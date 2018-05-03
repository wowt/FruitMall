package com.hongcheng.fruitmall.common.exception;

import com.google.common.base.Strings;
import com.hongcheng.fruitmall.common.constants.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static com.hongcheng.fruitmall.common.constants.RestResponseCode.CUSTOMER_ERROR;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class RestExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler({BusinessException.class,Exception.class})
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse<Strings> handleBusinessException(HttpServletRequest request, Exception exception) {

        logger.error("请求失败:" + request.getRequestURI(), exception);
        //restful url 请求错误日志记录
        restfulUrlErrorLog(request, exception);
        return RestResponse.error(CUSTOMER_ERROR, exception.getMessage());
    }

    private void restfulUrlErrorLog(HttpServletRequest request, Throwable e) {
            logger.error("RESTful请求失败", e.getMessage());
    }
}
