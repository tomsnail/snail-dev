package cn.tomsnail.snail.core.objsql.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages={"cn.tomsnail.snail.core.objsql.util","cn.tomsnail.snail.core.objsql.db.jdbc"})
public class JDBCAnnotation {

}
