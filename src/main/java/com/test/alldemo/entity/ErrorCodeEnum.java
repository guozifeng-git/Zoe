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
    STOCK_NOT_ENOUGH("1","库存不足")
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
