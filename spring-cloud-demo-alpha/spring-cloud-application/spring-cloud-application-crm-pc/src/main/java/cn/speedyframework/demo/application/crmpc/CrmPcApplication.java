package cn.speedyframework.demo.application.crmpc;

import cn.speedyframework.demo.common.bean.EnableSpeedyBeanAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-08 09:45
 */
@SpringBootApplication
@EnableSpeedyBeanAutoConfiguration
@EnableConfigurationProperties
public class CrmPcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmPcApplication.class, args);
    }
}
