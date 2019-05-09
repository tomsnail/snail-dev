package cn.tomsnail.snail.core.framework.validator;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SnailValidator {

    public Class<?> mapClass() default Object.class;
    @AliasFor("valid")
    public boolean value() default true;
    @AliasFor("value")
    public boolean valid() default true;
}
