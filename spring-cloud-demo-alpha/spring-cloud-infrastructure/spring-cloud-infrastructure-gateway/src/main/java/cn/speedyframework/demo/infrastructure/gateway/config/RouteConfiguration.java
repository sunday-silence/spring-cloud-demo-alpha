package cn.speedyframework.demo.infrastructure.gateway.config;

import cn.speedyframework.demo.infrastructure.gateway.filter.Test2Filter;
import cn.speedyframework.demo.infrastructure.gateway.filter.TestFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author chenguangxue@zbj.com
 * @date 2019-08-21 10:56
 */
//@Configuration
@Slf4j
public class RouteConfiguration {

    @Autowired
    private TestFilter testFilter;
    @Autowired
    private Test2Filter test2Filter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route("TEST_READ_REQUEST_BODY",
//                        p -> p.readBody(String.class, requestBody -> true)
//                                .and().alwaysTrue()
//                                .filters(f -> f.filters(testFilter, test2Filter).stripPrefix(1))
//                                .uri("lb://crm-pc"))
                .build();
    }
}
