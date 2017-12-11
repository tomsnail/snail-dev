package cn.tomsnail.jms.kafka;

import cn.tomsnail.jms.MQConfig;

/**
 *        kafka消息模型
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月8日 上午10:42:51
 * @see 
 */
public class KafkaMQConfig extends  MQConfig{

	public static final String MQ_CONFIG_CLASS = "cn.tomsnail.jms.kafka.KafkaMQConfig";
	
	public static final String FACTORY_CLASS = "cn.tomsnail.jms.kafka.consumer.KafkaConsumerFactory";
	
}
