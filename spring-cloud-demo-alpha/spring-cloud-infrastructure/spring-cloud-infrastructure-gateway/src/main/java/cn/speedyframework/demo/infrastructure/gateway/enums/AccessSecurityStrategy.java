package cn.speedyframework.demo.infrastructure.gateway.enums;


import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 访问系统的安全策略
 *
 * @author chenguangxue@zbj.com
 * @date 2019-08-20 16:49
 */
public enum AccessSecurityStrategy {

    // 校验请求头是否包含了所有必须的参数信息
    NECESSARY_HEADERS(exchange -> {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        return CustomRequestHeader.NECESSARY_HEADER_NAMES
                .stream()
                .allMatch(customRequestHeader -> {
                    List<String> value = headers.get(customRequestHeader.name());
                    return value != null && !value.isEmpty();
                });
    }),

    // 校验访问签名是否合法
    LEGAL_ACCESS_TOKEN(exchange -> true),
    ;

    public static final List<Predicate<ServerWebExchange>> PREDICATES = EnumSet.allOf(AccessSecurityStrategy.class)
            .stream()
            .map(accessSecurityStrategy -> accessSecurityStrategy.predicate)
            .collect(Collectors.toList());

    private final Predicate<ServerWebExchange> predicate;

    AccessSecurityStrategy(Predicate<ServerWebExchange> predicate) {
        this.predicate = predicate;
    }
}
