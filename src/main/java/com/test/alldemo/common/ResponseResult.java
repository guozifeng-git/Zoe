package com.test.alldemo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 类名称：ResponseResult
 * ********************************
 * <p>
 * 类描述：通用返回结果模型
 *
 * @author admin
 */
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -5727146833153928583L;

    private String code;

    private Boolean isSuccess;

    private String describe;

    private T result;

    public static <T> ResponseResult<T> success(T result) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setIsSuccess(Boolean.TRUE);
        responseResult.setCode(ErrorCodeEnum.SUCCESS.getCode());
        responseResult.setResult(result);

        return responseResult;
    }

    public static <T> ResponseResult<T> failure(String code, String describe) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setIsSuccess(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setDescribe(describe);

        return responseResult;
    }

    public static <T> ResponseResult<T> failure(ErrorCodeEnum codeEnum) {
        return failure(codeEnum.getCode(), codeEnum.getDescribe());
    }

}
