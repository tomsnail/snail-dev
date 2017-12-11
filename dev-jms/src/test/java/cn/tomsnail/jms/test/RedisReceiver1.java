package cn.tomsnail.jms.test;

import cn.tomsnail.jms.IJmsReceiveCall;
import cn.tomsnail.jms.JmsType;
import cn.tomsnail.jms.Message;
import cn.tomsnail.jms.spring.annotation.JmsConsumer;

//@JmsConsumer(url="192.168.169.156:2181",name="testmsg",type=JmsType.KAFKA,group="test")
public class RedisReceiver1 implements IJmsReceiveCall{

	@Override
	public void call(Message msg) {
		System.out.println(Thread.currentThread().getName()+" "+msg.getMessage());
	}

}
