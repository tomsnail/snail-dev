package cn.tomsnail.snail.example.ext.spring;


import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SpringAsyncService {
	
	
	@Async
	public void asyncCall() {
		System.out.println("asyncCall start......");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("asyncCall end......");
	}

}
