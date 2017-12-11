package cn.tomsnail.i.test;

import org.springframework.stereotype.Component;

import cn.tomsnail.i.core.DataContentPckt;
import cn.tomsnail.jms.IJmsSender;
import cn.tomsnail.jms.JmsType;
import cn.tomsnail.jms.spring.annotation.JmsSender;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午3:20:59
 * @see 
 */
@Component
public class SendDemo {
	
	@JmsSender(name="email",url="192.168.169.150",type=JmsType.REDIS)
	private IJmsSender emailJmsSender; 
	
	@JmsSender(name="sms",url="192.168.169.150",type=JmsType.REDIS)
	private IJmsSender smsJmsSender; 

	public void demo(){
		smsJmsSender.send("sms", new DataContentPckt().toString());
	}
	
}
