package test;

import cn.speedyframework.demo.common.core.enums.DefaultExceptionResultStatus;
import cn.speedyframework.demo.common.core.enums.DefaultNormalResultStatus;
import cn.speedyframework.demo.common.core.exception.GlobalException;
import org.junit.Test;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-07 15:06
 */
public class Test1 {

    @Test
    public void test() {
        DefaultNormalResultStatus.SUCCESS.flowResult().response();
        DefaultNormalResultStatus.FAILED.responseResult("");

        GlobalException globalException = DefaultExceptionResultStatus.DATABASE_ERROR.buildException();
    }
}
