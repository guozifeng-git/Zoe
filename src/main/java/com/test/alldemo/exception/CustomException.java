package com.test.alldemo.exception;

import com.test.alldemo.entity.ErrorCodeEnum;
import lombok.Getter;

/**
 * @author admin
 */
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 4586120992784148945L;
    @Getter
    private final String code;

    /**
     * 枚举构造业务异常
     * @param errorCodeEnum 枚举
     */
    public CustomException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDescribe());
        this.code = errorCodeEnum.getCode();
    }

    /**
     * 自定义消息体构造业务类异常
     * @param errorCodeEnum 枚举
     * @param describe 自定义消息
     */
    public CustomException(ErrorCodeEnum errorCodeEnum,String describe){
        super(describe);
        this.code = errorCodeEnum.getCode();
    }
}
