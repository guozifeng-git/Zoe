package com.test.alldemo.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author admin
 */

@Getter
public enum ErrorCodeEnum {
    //库存不足
    STOCK_NOT_ENOUGH("1","Insufficient inventory"),
    //获取锁失败
    TRY_ACQUIRE_FAIL("2","Failed to acquire lock")
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
