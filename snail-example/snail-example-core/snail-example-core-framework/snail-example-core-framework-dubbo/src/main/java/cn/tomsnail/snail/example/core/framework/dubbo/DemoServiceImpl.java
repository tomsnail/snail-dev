package cn.tomsnail.snail.example.core.framework.dubbo;

import cn.tomsnail.snail.core.log.annotation.LogPoint;
import org.springframework.stereotype.Component;

@Component("demoService")
public class DemoServiceImpl implements DemoService {
    @Override
    @LogPoint
    public String hello(String name) {
        return "hello "+name;
    }
}
