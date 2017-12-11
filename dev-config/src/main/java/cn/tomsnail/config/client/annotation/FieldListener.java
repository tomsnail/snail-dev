package cn.tomsnail.config.client.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *        字段监听
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年7月22日 下午5:18:40
 * @see 
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldListener {

}
