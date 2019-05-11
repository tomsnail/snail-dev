package cn.tomsnail.snail.ext.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *        缓存基础扫描注解，在需要调用缓存的的class上进行注解，主要用于spring加载时识别该类为需要缓存的类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午2:33:29
 * @see 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@ComponentScan(basePackages="cn.tomsnail.snail.ext.cache.spring")
public @interface SnailCache {
	
}
