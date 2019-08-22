package cn.speedyframework.demo.infrastructure.gateway.filter;

import cn.speedyframework.demo.common.core.interfaces.InitializingLogBean;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.CACHED_REQUEST_BODY_ATTR;

/**
 * @author chenguangxue@zbj.com
 * @date 2019-08-20 17:49
 */
@Slf4j
//@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AccessLogFilter implements GlobalFilter, InitializingLogBean {

    @Override
    public Logger logger() {
        return log;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Object body = exchange.getAttributeOrDefault(CACHED_REQUEST_BODY_ATTR, null);
        log.info("requestBody：{}", body);
        return chain.filter(exchange);
    }
}
