package cn.tomsnail.snail.core.ds.mybatis.autoconfig.single;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"cn.tomsnail.snail.core.ds.mybatis.autoconfig.mybaits","cn.tomsnail.snail.core.ds.autoconfig.single"})
public class AutoSingleConfig {
}
