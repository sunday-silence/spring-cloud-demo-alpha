package cn.speedyframework.demo.common.bean.advice;

import cn.speedyframework.demo.common.bean.InitializingLogBean;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

/**
 * @description 针对请求参数有@RequestBody注解的方法
 * @name chenguangxue
 * @date 2019-08-08 09:19
 */
@RestControllerAdvice
@Slf4j
@ConditionalOnExpression("${speedy.bean.enable-request-body-advice:true}")
public class SpeedyRequestBodyAdvice extends RequestBodyAdviceAdapter implements InitializingLogBean {

    @Override
    public boolean supports(MethodParameter methodParameter, Type type
            , Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    // 请求体已经读取之后的处理方法
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter
            , Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (body != null) {
            log.info("请求数据：\n{}", body);
        }
        return body;
    }

    @Override
    public Logger logger() {
        return log;
    }
}
