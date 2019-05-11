package cn.tomsnail.snail.e3.mq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerTest {

	 	public final static String QUEUE_NAME="rabbitMQ.test";
	 	
//	 	private static final String IP = "127.0.0.1";
	 	
//	 	private static final String USER_NAME = "";
//	 	
//	 	private static final String PASSWORD = "";
//	 	
//	 	private static final String PORT = "2088";
	 	

	    public static void main(String[] args) throws IOException, TimeoutException {
	        //创建连接工厂
	        ConnectionFactory factory = new ConnectionFactory();
	        //设置RabbitMQ相关信息
	        factory.setHost("");
	        factory.setUsername("lp");
	        factory.setPassword("");
	        factory.setPort(2088);
	        //创建一个新的连接
	        Connection connection = factory.newConnection();
	        //创建一个通道
	        Channel channel = connection.createChannel();
	        String message = "Hello RabbitMQ";
	        //发送消息到队列中
	        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
	        System.out.println("Producer Send +'" + message + "'");
	        channel.close();
	        connection.close();
	        
	    }
	
}
