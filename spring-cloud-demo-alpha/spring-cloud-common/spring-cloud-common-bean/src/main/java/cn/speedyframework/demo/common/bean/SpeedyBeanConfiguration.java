package cn.speedyframework.demo.common.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @description 通用组件的配置
 * @name chenguangxue
 * @date 2019-08-08 10:30
 */
@Component
@ConfigurationProperties(prefix = "speedy.bean")
@ComponentScan("cn.speedyframework.demo.common.bean")
@EnableConfigurationProperties
@Data
public class SpeedyBeanConfiguration {

    /*
     * @EnableConfigurationProperties 和 @Component 必须一起使用，否则在properties文件中无法提示
     * */

    // 自定义请求体拦截器的开关
    private boolean enableRequestBodyAdvice = true;

    // 自定义异常处理器的开关
    private boolean enableExceptionHandler = true;
}
