package cn.tomsnail.http.client.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 *        httprest客户端服务类注解，用于扫描过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月24日 下午4:36:37
 * @see 
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@ComponentScan("cn.tomsnail.http.client.spring")
public @interface HttpClientServiceTraget {


	
}
