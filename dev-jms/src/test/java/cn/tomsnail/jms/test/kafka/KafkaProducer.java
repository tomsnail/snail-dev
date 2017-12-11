package cn.tomsnail.jms.test.kafka;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月7日 上午10:29:04
 * @see 
 */
public class KafkaProducer extends Thread{
	 private String topic;  
     
	    public KafkaProducer(String topic){  
	        super();  
	        this.topic = topic;  
	    }  
	      
	      
	    @Override  
	    public void run() {  
	        Producer producer = createProducer();  
	        
	        int i=0;  
	        while(true){  
	        	String message = "message:" + i++;
	            producer.send(new KeyedMessage<String, String>(topic, i+"",message));  
	            try {  
	                TimeUnit.SECONDS.sleep(1);  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	  
	    private Producer createProducer() {  
	        Properties properties = new Properties();  
	      //  properties.put("zookeeper.connect", "192.168.169.150:2181");//声明zk  
	        properties.put("serializer.class", StringEncoder.class.getName());  
	        properties.put("metadata.broker.list", "192.168.169.150:8457");// 声明kafka broker  

	        return new Producer<String, String>(new ProducerConfig(properties));  
	     }  
	      
	      
	    public static void main(String[] args) {  
	        new KafkaProducer("test4").start();// 使用kafka集群中创建好的主题 test   
	          
	    }  
}
