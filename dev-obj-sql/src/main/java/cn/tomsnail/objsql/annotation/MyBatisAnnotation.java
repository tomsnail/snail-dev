package cn.tomsnail.objsql.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages={"cn.tomsnail.objsql.util","cn.tomsnail.objsql.db.mybatis"})
public class MyBatisAnnotation {

}
