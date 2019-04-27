package cn.tomsnail.snail.example.core.framework.dubbo.annotation.client;

import cn.tomsnail.snail.core.util.date.Jdk8DateTimeUtil;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DemoServiceClient {

    @Reference
    private DemoService demoService;

    @PostConstruct
    public void test(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println(demoService.hello(Jdk8DateTimeUtil.getNowDateTimeStr()));
        }
    }

}
