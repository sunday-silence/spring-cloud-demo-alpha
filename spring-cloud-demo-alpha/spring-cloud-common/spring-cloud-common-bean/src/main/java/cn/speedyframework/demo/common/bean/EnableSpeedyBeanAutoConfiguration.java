package cn.speedyframework.demo.common.bean;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @description 开启speedy通用组件的注解
 * @name chenguangxue
 * @date 2019-08-08 10:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SpeedyBeanConfiguration.class)
public @interface EnableSpeedyBeanAutoConfiguration {
}
