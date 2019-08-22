package cn.speedyframework.demo.infrastructure.gateway.filter;

import cn.speedyframework.demo.common.core.enums.DefaultNormalResultStatus;
import cn.speedyframework.demo.common.core.interfaces.InitializingLogBean;
import cn.speedyframework.demo.common.core.stereotype.ResponseResult;
import cn.speedyframework.demo.infrastructure.gateway.enums.AccessSecurityStrategy;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.CACHED_REQUEST_BODY_ATTR;

/**
 * 安全访问的统一拦截器，必须通过这个拦截器才会转发到真正的服务
 *
 * @author chenguangxue@zbj.com
 * @date 2019-08-19 18:02
 */
//@Component
@Slf4j
public class AccessSecurityFilter implements GlobalFilter, InitializingLogBean {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Object attribute = exchange.getAttribute(CACHED_REQUEST_BODY_ATTR);
        log.info("requestBody：{}", attribute);

        boolean allMatch = AccessSecurityStrategy.PREDICATES.stream().allMatch(predicate -> predicate.test(exchange));
        if (allMatch) {
            return chain.filter(exchange);
        } else {
            return failResponse(exchange);
        }
    }

    /**
     * 生成校验失败的响应
     */
    private Mono<Void> failResponse(ServerWebExchange exchange) {
        ResponseResult<Void> responseResult = DefaultNormalResultStatus.ILLEGAL_ACCESS.response();
        byte[] bytes = JSON.toJSONString(responseResult).getBytes(StandardCharsets.UTF_8);

        ServerHttpResponse response = exchange.getResponse();
        DataBuffer dataBuffer = response.bufferFactory().wrap(bytes);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public Logger logger() {
        return log;
    }
}
