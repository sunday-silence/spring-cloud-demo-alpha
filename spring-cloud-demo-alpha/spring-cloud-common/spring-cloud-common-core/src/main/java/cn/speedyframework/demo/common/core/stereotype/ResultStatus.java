package cn.speedyframework.demo.common.core.stereotype;

/**
 * @description 描述结果状态的接口
 * @name chenguangxue
 * @date 2019-08-02 15:10
 */
public interface ResultStatus {

    /* 结果是否成功 */
    boolean isSuccess();

    /* 结果的状态码 */
    String getCode();

    /* 结果的描述 */
    String getMessage();

    /* 快速判断结果是否为失败 */
    default boolean failed() {
        return !isSuccess();
    }
}
