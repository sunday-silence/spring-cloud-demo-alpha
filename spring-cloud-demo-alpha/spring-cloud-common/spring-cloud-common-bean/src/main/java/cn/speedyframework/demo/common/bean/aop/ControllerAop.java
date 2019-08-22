package cn.speedyframework.demo.common.bean.aop;

import cn.speedyframework.demo.common.core.annotation.DisableAop;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-13 17:56
 */
@Aspect
@Configuration      // 在aop的配置类加上这个注解，该配置才会生效，否则不会起作用
@Slf4j
public class ControllerAop {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping))")
    public void pointcut() {
    }

    // 是否需要关闭aop
    // 暂时不考虑aop的类型
    private boolean disableAop(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();

        // 首先判断方法本身有没有注解
        boolean disableAop = signature.getMethod().isAnnotationPresent(DisableAop.class);
        if (disableAop) {
            return true;
        }

        // 如果方法没有注解，再进一步判断所在类是否有注解
        Class declaringType = signature.getDeclaringType();
        return declaringType.isAnnotationPresent(DisableAop.class);
    }

    private String requestUrl() {
        return null;
    }

    @Before("pointcut()")
    public void outputLog(JoinPoint point) {
        boolean disableAop = disableAop(point);
        if (disableAop) {
            return;
        }

        MethodSignature signature = (MethodSignature) point.getSignature();
        Annotation[] declaredAnnotations = signature.getMethod().getDeclaredAnnotations();

        RequestMapping requestMapping = AnnotationUtils.findAnnotation(signature.getMethod(), RequestMapping.class);
        log.info("requestMapping：{}", Arrays.toString(requestMapping.value()));

        Object[] args = point.getArgs();
        if (args == null || args.length == 0) {
            return;
        }

        if (args.length == 1) {
            log.info("请求参数：{}", JSON.toJSONString(args[0]));
            return;
        }

        List<String> objects = new ArrayList<>(args.length);
        for (Object o : args) {
            String string = JSON.toJSONString(o);
            objects.add(string);
        }
        log.info("请求参数：{}", objects);
    }
}
