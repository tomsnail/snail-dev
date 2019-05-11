package cn.tomsnail.snail.ext.pilot.hot.reload.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Autowired(required=false)
public @interface HotLoadObject {
	
	public HotLoadType type() default HotLoadType.singleton;
	
	public String name() default "";
	
	public boolean isBean() default false;
	
}
