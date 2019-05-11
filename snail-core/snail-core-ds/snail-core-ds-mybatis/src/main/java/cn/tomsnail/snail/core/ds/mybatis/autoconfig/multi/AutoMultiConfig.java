package cn.tomsnail.snail.core.ds.mybatis.autoconfig.multi;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"cn.tomsnail.snail.core.ds.mybatis.autoconfig.mybaits","cn.tomsnail.snail.core.ds.autoconfig.multi"})
public class AutoMultiConfig {
}
