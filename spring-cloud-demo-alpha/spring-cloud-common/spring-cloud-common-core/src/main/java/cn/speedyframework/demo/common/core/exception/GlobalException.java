package cn.speedyframework.demo.common.core.exception;

import cn.speedyframework.demo.common.core.stereotype.ExceptionResultStatus;
import lombok.Getter;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-07 11:17
 */
@Getter
public final class GlobalException extends RuntimeException {

    private final ExceptionResultStatus status;

    // 通过异常状态码来生成异常
    public GlobalException(ExceptionResultStatus status) {
        this(status, status.getMessage());
    }

    // 通过异常状态码来生成异常，并且指定异常信息
    public GlobalException(ExceptionResultStatus status, String message) {
        super(message);
        this.status = status;
    }

    public GlobalException(Throwable e, String message, ExceptionResultStatus status) {
        super(message, e);
        this.status = status;
    }

    public GlobalException(Throwable e, ExceptionResultStatus status) {
        this(e, status.getMessage(), status);
    }
}
