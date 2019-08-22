package cn.speedyframework.demo.common.core.enums;

import cn.speedyframework.demo.common.core.stereotype.NormalResultStatus;
import lombok.Getter;

/**
 * 系统默认的结果状态，不包含异常结果状态
 *
 * @author chenguangxue
 * @date 2019-08-02 15:19
 */
@Getter
public enum DefaultNormalResultStatus implements NormalResultStatus {

    SUCCESS(true, "0000", "成功"),

    FAILED("4000", "失败"),
    ILLEGAL_ACCESS("4010", "非法请求"),
    ;

    // 是否处理成功
    private final boolean success;
    private final String code;
    private final String message;

    DefaultNormalResultStatus(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    DefaultNormalResultStatus(String code, String message) {
        this(false, code, message);
    }
}
