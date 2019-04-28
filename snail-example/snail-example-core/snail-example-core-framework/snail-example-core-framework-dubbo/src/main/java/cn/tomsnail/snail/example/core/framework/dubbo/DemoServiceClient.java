package cn.tomsnail.snail.example.core.framework.dubbo;

import cn.tomsnail.snail.core.util.date.Jdk8DateTimeUtil;
import org.springframework.stereotype.Component;

public class DemoServiceClient {

    private DemoService demoService;

    public void test(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            try {
				System.out.println(demoService.hello(Jdk8DateTimeUtil.getNowDateTimeStr()));
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

    public DemoService getDemoService() {
        return demoService;
    }

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }
}
