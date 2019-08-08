package cn.speedyframework.demo.common.core.stereotype;

import cn.speedyframework.demo.common.core.exception.GlobalException;

/**
 * @description 描述异常结果状态的接口
 * @name chenguangxue
 * @date 2019-08-02 15:15
 */
public interface ExceptionResultStatus extends ResultStatus {

    // 创建异常对象
    default GlobalException buildException(Throwable e, String message) {
        return new GlobalException(e, message, this);
    }

    // 创建异常对象
    default GlobalException buildException(Throwable e) {
        return buildException(e, this.getMessage());
    }

    // 创建异常对象
    default GlobalException buildException(String message) {
        return new GlobalException(this, message);
    }

    default GlobalException buildException() {
        return new GlobalException(this);
    }

    /* 异常结果肯定是false */
    @Override
    default boolean isSuccess() {
        return false;
    }
}
