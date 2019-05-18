package cn.tomsnail.snail.ext.mq.core.kafka.sender;

import java.io.Serializable;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;

import cn.tomsnail.snail.ext.mq.core.IJmsSender;
import cn.tomsnail.snail.ext.mq.core.MQConfig;
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//import kafka.serializer.StringEncoder;


/**
 *        kafka生成者
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:18:02
 * @see 
 */
public class KafkaProducer implements IJmsSender, Runnable {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	private MQConfig kafkaMQConfig;

//	Producer producer;

	public KafkaProducer(MQConfig kafkaMQConfig) {
		this.kafkaMQConfig = kafkaMQConfig;
//		producer = createProducer();
	}

	public KafkaProducer() {

	}

	@Override
	public void run() {

	}

	@Override
	public void send(String topic, Serializable obj) {
//		if (producer != null)
//			try {
//				producer.send(new KeyedMessage<String, String>(topic, System.currentTimeMillis() + "", objectMapper.writeValueAsString(obj)));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
	}

	@Override
	public void send(String topic, String msg) {
//		if (producer != null)
//			try {
//				producer.send(new KeyedMessage<String, String>(topic, System.currentTimeMillis() + "", msg));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
	}

	/**
	 *        创建生产者
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:18:16
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
//	private Producer createProducer() {
//		Properties properties = new Properties();
//		properties.put("serializer.class", StringEncoder.class.getName());
//		properties.put("metadata.broker.list", kafkaMQConfig.getUrl());
//		return new Producer<String, String>(new ProducerConfig(properties));
//	}


	public MQConfig getKafkaMQConfig() {
		return kafkaMQConfig;
	}

	public void setKafkaMQConfig(MQConfig kafkaMQConfig) {
		this.kafkaMQConfig = kafkaMQConfig;
	}
}
