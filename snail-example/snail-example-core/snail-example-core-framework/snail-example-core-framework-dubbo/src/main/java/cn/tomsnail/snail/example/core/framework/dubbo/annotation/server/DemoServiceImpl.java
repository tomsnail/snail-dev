package cn.tomsnail.snail.example.core.framework.dubbo.annotation.server;

import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

@Service(protocol = "dubbo",registry = "zk")
public class DemoServiceImpl implements DemoService {

    @Override
    @LogPoint
    public String hello(String name) {
        return "hello "+name;
    }
}
