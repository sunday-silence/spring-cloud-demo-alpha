package cn.speedyframework.demo.common.core.stereotype;

import lombok.Getter;

/**
 * @description 流程处理结果（不包含数据传输）
 * @name chenguangxue
 * @date 2019-08-07 11:37
 */
@Getter
public final class FlowResult {

    private final boolean success;
    private final String code;
    private final String message;

    private FlowResult(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static FlowResult of(boolean success, String code, String message) {
        return new FlowResult(success, code, message);
    }

    // 转换为响应结果，并附带响应数据
    public <T> ResponseResult<T> response(T data) {
        return ResponseResult.of(this, data);
    }

    // 转换为响应结果
    public <T> ResponseResult<T> response() {
        return response(null);
    }
}
