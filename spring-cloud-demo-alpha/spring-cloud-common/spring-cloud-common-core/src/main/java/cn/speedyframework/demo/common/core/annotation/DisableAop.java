package cn.speedyframework.demo.common.core.annotation;

import java.lang.annotation.*;

/**
 * @description 用于关闭aop的注解，如果类或者方法上面加上了这个注解，则不会执行aop
 * @name chenguangxue
 * @date 2019-08-09 23:52
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DisableAop {

    // 由speedy系统所提供的aop类型，可以由用户自定义来选择是否关闭
    enum SpeedyAopType {
    }

    // 如果这个数组为空，则默认关闭所有的aop操作
    SpeedyAopType[] value() default {};
}
