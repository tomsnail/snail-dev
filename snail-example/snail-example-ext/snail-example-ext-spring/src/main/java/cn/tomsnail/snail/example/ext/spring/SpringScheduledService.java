package cn.tomsnail.snail.example.ext.spring;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringScheduledService {
	
	@Scheduled(fixedDelayString="${spring.schedule.test.fiexd.delay}")
	@Async
	public void job() {
		System.out.println("do job!!");
	}

}
