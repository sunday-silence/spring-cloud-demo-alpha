package cn.speedyframework.demo.common.core.stereotype;

/**
 * @description 代表正常处理的结果状态
 * @name chenguangxue
 * @date 2019-08-07 11:15
 */
public interface NormalResultStatus extends ResultStatus {

    // 通过正常结果状态码生成流程结果的方法
    default FlowResult flowResult() {
        return flowResult(this.getMessage());
    }

    default FlowResult flowResult(String message) {
        return FlowResult.of(isSuccess(), getCode(), message);
    }

    // 通过正常结果状态码生成响应结果的方法
    default <T> ResponseResult<T> responseWithData(String message, T data) {
        return ResponseResult.of(isSuccess(), getCode(), message, data);
    }

    default <T> ResponseResult<T> responseWithData(T data) {
        return responseWithData(getMessage(), data);
    }

    default <T> ResponseResult<T> response(String message) {
        return responseWithData(message, null);
    }

    default <T> ResponseResult<T> response() {
        return responseWithData(getMessage(), null);
    }
}
