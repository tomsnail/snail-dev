package cn.tomsnail.snail.example.ext.mq.kafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import cn.tomsnail.snail.core.util.uuid.UuidUtil;

@Service
public class KafkaSendService {
	
	 	@Autowired
	    private KafkaTemplate kafkaTemplate;

	    /**
	     * 定时任务
	     */
		
	    public void send(String message){
	        ListenableFuture future = kafkaTemplate.send("test", message);
	        future.addCallback(o -> System.out.println("send-消息发送成功：" + message), throwable -> System.out.println("消息发送失败：" + message));
	    }
	    
	    @Scheduled(fixedDelayString="${spring.schedule.test.fiexd.delay}")
		@Async
	    public void async() {
	    	send(UuidUtil.getNUUID());
	    }

}
