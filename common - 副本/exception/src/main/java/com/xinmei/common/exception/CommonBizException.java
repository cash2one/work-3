package com.xinmei.common.exception;

/**
 * @ClassName: com.xinmei.common.exception.CommonBizException
 * @Description: 通用业务异常模型
 * @Author xbzhu
 * @Date 2017年03月27日 10:30
 */
public class CommonBizException extends RuntimeException{

    public CommonBizException() {
    }

    public CommonBizException(String message) {
        super(message);
    }

    public CommonBizException(Throwable cause) {
        super(cause);
    }

    public CommonBizException(String message, Throwable cause) {
        super(message, cause);
    }
}
