package cn.tomsnail.snail.core.config.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *        配置监听，用于对需要配置的类，进行扫描和筛选
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月22日 下午5:18:07
 * @see 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@ComponentScan("cn.tomsnail.snail.core.config.client.plugin")
public @interface ConfigListener {
	Scope scope() default Scope.ALL;
}
