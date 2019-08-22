package cn.speedyframework.demo.application.crmpc.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @description
 * @name chenguangxue
 * @date 2019-08-08 14:48
 */
@Data
public class TestRequest {

    @Length(min = 6, max = 30)
    private String name;

    @NotNull
    @Pattern(regexp = "^1[35789][0-9]{9}$")
    private String mobile;
}
