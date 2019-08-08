package cn.speedyframework.demo.common.bean.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-08 09:19
 */
@Component
public class CustomRequestBodyAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type type
            , Class<? extends HttpMessageConverter<?>> aClass) {
        Annotation[] parameterAnnotations = methodParameter.getParameterAnnotations();

        for (Annotation annotation : parameterAnnotations) {
            System.out.println(annotation.annotationType());
        }

        return false;
    }
}
