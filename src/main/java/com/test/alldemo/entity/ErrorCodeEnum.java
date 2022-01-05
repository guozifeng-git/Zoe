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
    STOCK_NOT_ENOUGH("1","Insufficient inventory"),
    TRY_ACQUIRE_FAIL("2","Failed to acquire lock"),
    USER_DOES_NOT_EXIST("3","User does not exist"),
    ITEM_DOES_NOT_EXIST("4","Item does not exist"),
    CHECK_FAIL("5","Redis check failed"),
    SUCCESS("6","Success")
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
