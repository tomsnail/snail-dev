package cn.tomsnail.snail.example.core.framework.dubbo.annotation.client;

import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import cn.tomsnail.snail.example.core.framework.dubbo.ModelObject;
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
            try {
                ModelObject modelObject = new ModelObject();
                System.out.println(demoService.hello(System.currentTimeMillis()+""));
                System.out.println(demoService.vtest(modelObject));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
        }
    }

}
