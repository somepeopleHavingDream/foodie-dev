package org.yangxin.pojo.annotation;

import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.*;

/**
 * 手机
 * 验证手机号，空和正确的手机号都能验证通过
 * 正确的手机号由11位数字组成，第一位为1，第二位为3,4,5,7,8
 *
 * @author yangxin
 * 2019/12/05 11:08
 */
@ConstraintComposition
@Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}")
@Length(min = 11, max = 11)
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
    String message() default "手机号校验错误";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
