package cn.speedyframework.demo.application.crmpc.controller;

import cn.speedyframework.demo.application.crmpc.request.TestRequest;
import cn.speedyframework.demo.common.core.enums.DefaultNormalResultStatus;
import cn.speedyframework.demo.common.core.stereotype.ResponseResult;
import cn.speedyframework.demo.common.core.util.DateTimeUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-08 10:49
 */
@RestController
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @PostMapping(value = "/timestamp")
    public ResponseResult<Long> timestamp(@RequestBody TestRequest request) {
        log.info("TestController：{}", JSON.toJSONString(request));
        return DefaultNormalResultStatus.SUCCESS.response();
    }
}
