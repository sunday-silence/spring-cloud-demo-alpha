package cn.speedyframework.demo.common.core.stereotype;

import lombok.Getter;

/**
 * @description 响应结果（包含数据）
 * @name chenguangxue
 * @date 2019-08-07 11:47
 */
@Getter
public final class ResponseResult<T> {

    private final boolean success;
    private final String code;
    private final String message;
    private final T data;
    private final String timestamp;

    private ResponseResult(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

    public static <T> ResponseResult<T> of(boolean success, String code, String message, T data) {
        return new ResponseResult<>(success, code, message, data);
    }
}
