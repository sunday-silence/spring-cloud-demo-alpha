package cn.speedyframework.demo.common.bean;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-08 11:01
 */
public interface InitializingLogBean extends InitializingBean {

    // 日志工具必须由具体的实现类来提供，因为接口无法添加添加(@Slf4j)注解
    Logger logger();

    @Override
    default void afterPropertiesSet() throws Exception {
        logger().info("-----> component {} init complete", this.getClass().getName());
    }
}
