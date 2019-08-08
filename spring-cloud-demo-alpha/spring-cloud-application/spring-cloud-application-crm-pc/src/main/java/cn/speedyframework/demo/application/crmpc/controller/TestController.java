package cn.speedyframework.demo.application.crmpc.controller;

import cn.speedyframework.demo.application.crmpc.request.TestRequest;
import cn.speedyframework.demo.common.core.enums.DefaultNormalResultStatus;
import cn.speedyframework.demo.common.core.stereotype.ResponseResult;
import cn.speedyframework.demo.common.core.util.DateTimeUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-08 10:49
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PostMapping(value = "/timestamp")
    public ResponseResult<Long> timestamp(@RequestBody @Valid TestRequest request) {
        return DefaultNormalResultStatus.SUCCESS.responseWithData(DateTimeUtils.timestamp());
    }
}
