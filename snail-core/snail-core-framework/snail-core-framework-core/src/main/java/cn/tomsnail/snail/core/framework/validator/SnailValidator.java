package cn.tomsnail.snail.core.framework.validator;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SnailValidator {

    public Class<?> mapClass() default Object.class;

    public boolean value() default true;
}
