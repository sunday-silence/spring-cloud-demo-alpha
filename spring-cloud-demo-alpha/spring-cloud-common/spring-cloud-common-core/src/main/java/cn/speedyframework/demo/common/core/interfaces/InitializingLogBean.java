package cn.speedyframework.demo.common.core.interfaces;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * 这个接口用于自动生成自定义组件的日志信息
 *
 * @author chenguangxue@zbj.com
 * @date 2019-08-20 10:35
 */
public interface InitializingLogBean extends InitializingBean {

    // 日志工具由实现类提供，也可以
    Logger logger();

    @Override
    default void afterPropertiesSet() throws Exception {
        logger().info("-----> component {} init complete", this.getClass().getName());
    }
}
