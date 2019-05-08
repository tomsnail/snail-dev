package cn.tomsnail.snail.example.core.framework.dubbo;


import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.example.core.framework.dubbo.annotation.server.ModelObject;
import org.springframework.stereotype.Component;

@Component("demoService")
public class DemoServiceImpl implements DemoService {


    @Override
    @LogPoint(level = "${demo.logger.level}")
    public String hello(String name) {
        return "hello "+name;
    }

    @Override
    public String vtest(ModelObject modelObject) {
        return null;
    }
}
