package cn.speedyframework.demo.infrastructure.gateway.enums;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 所有请求必须携带的自定义信息枚举
 *
 * @author chenguangxue@zbj.com
 * @date 2019-08-20 14:43
 */
@Getter
public enum CustomRequestHeader {

    APP_ID("应用ID"),
    TIMESTAMP("请求时间戳"),
    VERSION("应用版本号"),
    ACCESS_TOKEN("请求签名"),

    // 当登录校验成功之后，会将该token转换为用户id
    LOGIN_TOKEN("登录用户签名", false),
    ;

    // 包含所有枚举值的集合
    public static final Set<CustomRequestHeader> HEADER_NAMES =
            EnumSet.allOf(CustomRequestHeader.class);

    // 只包含必须携带枚举值的集合
    public static final Set<CustomRequestHeader> NECESSARY_HEADER_NAMES = HEADER_NAMES.stream()
            .filter(customRequestHeader -> customRequestHeader.necessary)
            .collect(Collectors.toSet());

    private final String description;
    private final boolean necessary;

    CustomRequestHeader(String description, boolean necessary) {
        this.description = description;
        this.necessary = necessary;
    }

    CustomRequestHeader(String description) {
        this(description, true);
    }
}
