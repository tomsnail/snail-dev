package com.zkjd.ehua.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;



import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.alibaba.druid.support.json.JSONUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;

public class MQUtil {
	
	
	private static final JmsTemplate JMS_TEMPLATE = SpringContextHolder.getBean(JmsTemplate.class);
	
	private static final ActiveMQDestination MQ_DESTINATION = SpringContextHolder.getBean("topicDestination");
	
	public static void sendNodeMsg(String type){
		if(StringUtils.isBlank(type)){
			return;
		}
		final Map<String,String> _map = new HashMap<String, String>();
		_map.put("type", type);
		JMS_TEMPLATE.send(MQ_DESTINATION, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage(JSONUtils.toJSONString(_map));  
            }  
        });  
		
	}
	public static void sendNodeMsg(String type,String id){
		if(StringUtils.isBlank(type)){
			return;
		}
		final Map<String,String> _map = new HashMap<String, String>();
		_map.put("type", type);
		_map.put("id", id);
		JMS_TEMPLATE.send(MQ_DESTINATION, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage(JSONUtils.toJSONString(_map));  
            }  
        });  
		
	}

}
