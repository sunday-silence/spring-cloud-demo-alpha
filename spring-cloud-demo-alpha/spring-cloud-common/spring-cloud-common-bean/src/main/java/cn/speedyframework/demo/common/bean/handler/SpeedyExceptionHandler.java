package cn.speedyframework.demo.common.bean.handler;

import cn.speedyframework.demo.common.bean.InitializingLogBean;
import cn.speedyframework.demo.common.core.enums.DefaultNormalResultStatus;
import cn.speedyframework.demo.common.core.stereotype.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @description 通用的异常处理器
 * @name chenguangxue
 * @date 2019-08-08 14:25
 */
@RestControllerAdvice
@Slf4j
@ConditionalOnExpression("${speedy.bean.enable-exception-handler:true}")
public class SpeedyExceptionHandler implements InitializingLogBean {

    @Override
    public Logger logger() {
        return log;
    }

    private <T> ResponseResult<T> dealFlow(Throwable e, Supplier<ResponseResult<T>> supplier) {
        log.error("异常信息：", e);
        return supplier.get();
    }

    @ExceptionHandler(value = ArithmeticException.class)
    public ResponseResult<Void> dealArithmeticException(ArithmeticException e) {
        return dealFlow(e, () -> DefaultNormalResultStatus.FAILED.response("发生数学异常"));
    }

    // 这个异常发生在请求体的格式错误，比如json格式错误
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult<Void> dealHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return dealFlow(e, () -> DefaultNormalResultStatus.FAILED.response("请求参数格式错误"));
    }

    // 这个异常发生在基于@Valid注解的请求参数校验失败
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<Map<String, List<String>>> dealMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, List<String>> map = new HashMap<>(bindingResult.getErrorCount());
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String field = fieldError.getField();
            List<String> messages = bindingResult.getFieldErrors(field).stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            map.put(field, messages);
        });

        return dealFlow(e, () -> {
            return DefaultNormalResultStatus.FAILED.responseWithData("请求参数校验失败", map);
        });
    }
}
