package com.test.alldemo.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author admin
 */

@Getter
public enum ErrorCodeEnum {
    //
    STOCK_NOT_ENOUGH("1","库存不足"),
    TRY_ACQUIRE_FAIL("2","获取锁失败"),
    USER_DOES_NOT_EXIST("3","用户不存在"),
    STOCK_DOES_NOT_EXIST("4","商品不存在")
    ;

    @EnumValue
    @JsonValue
    private final String code;
    private final String describe;

    ErrorCodeEnum(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }
}
