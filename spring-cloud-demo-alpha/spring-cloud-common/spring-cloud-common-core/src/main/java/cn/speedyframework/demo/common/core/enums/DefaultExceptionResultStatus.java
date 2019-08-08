package cn.speedyframework.demo.common.core.enums;

import cn.speedyframework.demo.common.core.stereotype.ExceptionResultStatus;
import lombok.Getter;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-02 15:38
 */
@Getter
public enum DefaultExceptionResultStatus implements ExceptionResultStatus {

    DATABASE_ERROR("数据库异常"),
    ;

    private final String code;
    private final String message;

    DefaultExceptionResultStatus(String message) {
        this.code = this.name().toUpperCase();
        this.message = message;
    }
}
