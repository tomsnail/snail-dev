package cn.tomsnail.snail.example.core.framework.dubbo.annotation.server;

import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Service(protocol = "dubbo",registry = "zk",validation = "true",retries = 1)
public class DemoServiceImpl implements DemoService {

    @Override
    @LogPoint
    public String hello( String name) {
        return "hello "+name;
    }

    @Override
    @LogPoint
    public String vtest(ModelObject modelObject) {
        return "hello "+modelObject.getId();
    }
}
