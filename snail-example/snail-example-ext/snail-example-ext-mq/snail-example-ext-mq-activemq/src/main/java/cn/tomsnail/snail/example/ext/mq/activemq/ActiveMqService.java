package cn.tomsnail.snail.example.ext.mq.activemq;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

@Component
public class ActiveMqService {

    private JmsMessagingTemplate  jmsTemplate;
	
	
	@Autowired
    public ActiveMqService(JmsMessagingTemplate template) {
        this.jmsTemplate = template;
    }

	
	
	@Scheduled(fixedDelayString="${spring.schedule.test.fiexd.delay}")
	@Async
	public void send() {
        jmsTemplate.convertAndSend(ConfigHelp.getInstance("config").getLocalConfig("spring.jms.template.default-destination", "ActiveMqService"),"test");

	}

    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void recivce(String message){
    	System.out.println("===============recivce======================");
        System.out.println(message);

    }

}
