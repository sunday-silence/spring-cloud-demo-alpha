package cn.speedyframework.demo.application.crmpc.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-08 14:48
 */
@Data
public class TestRequest {

    @NotBlank
    private String name;

    @NotNull
    @Pattern(regexp = "^1[35789][0-9]{9}$")
    private String mobile;
}
