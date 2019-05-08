# 消息

### 1 ActiveMQ

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-extends-mq-spring-activemq</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- spring扫描

  ```xml
  <!-- 非springboot项目须扫描该包 -->
  <context:component-scan base-package="cn.tomsnail.snail.ext.mq.activemq"/>
  ```

- 使用

  ```java
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
  
  ```

​	**发送**：

​		提供JmsMessagingTemplate

​		示例中是在构造方法提供的，在构造方法添加注解@Autowired

​	**接收**：

​		注解JmsListener，接收方法参数可以是自定义对象，但需要实现序列化接口（发送对象类同）

- 配置

  ```properties
  #activemq的url
  spring.activemq.broker-url=tcp://192.168.169.170:61616
  #activemq的用户名
  spring.activemq.user=
  #activemq的密码
  spring.activemq.password=
  #是否使用内存
  spring.activemq.in-memory=false
  #是否是订阅模式
  spring.jms.pub-sub-domain=false
  #队列名称，自定义
  spring.jms.template.default-destination=spring_test
  #是否使用连接池
  spring.activemq.pool.enabled=true
  #连接池最大数
  spring.activemq.pool.max-connections=50
  #序列化对象信任的类
  spring.activemq.packages.trusted=cn.tomsnail.snail.xxxx
  #序列化对象信任所有的类，配置为true,spring.activemq.packages.trusted不生效
  spring.activemq.packages.trust-all=true
  ```

### 2 RabbitMQ

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-extends-mq-spring-rabbitmq</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

  

- spring扫描

  ```xml
  <!-- 非springboot项目须扫描该包 -->
  <context:component-scan base-package="cn.tomsnail.snail.ext.mq.rabbitmq"/>
  ```

  

- 使用

  **发送**：

  实现ConfirmCallback接口，获得RabbitTemplate

  ```java
  @Service
  public class RabbitMQSendService implements ConfirmCallback{
  	
  	@Autowired
  	private RabbitTemplate rabbitTemplate = null;
  
  	@Override
  	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
  		if (ack) {
  			System.out.println("消息成功消费");
  		}else {
  			System.out.println("消息消费失败:" + cause);
  		}
  		
  	}
  	
  	public void send(String message) {
  		rabbitTemplate.setConfirmCallback(this);
  		rabbitTemplate.convertAndSend("rabbitmq.test", "", message);
  	}
  	
  }
  ```

  设置回调

  ```java
  rabbitTemplate.setConfirmCallback(this);
  ```

  发送消息

  ```java
  rabbitTemplate.convertAndSend("rabbitmq.test", "", message);
  ```

  **接收**：

  注解RabbitListener

  ```java
  @Component
  public class RabbitMQReceiveService {
  
  	@RabbitListener(queues = { "test" })
  	public void receiver(String message) {
  		System.out.println("收到消息: 【" + message + "】");
  	}
  	
  }
  ```

- 配置

  ```properties
  #RabbitMQ 配置，其他配置参加spring相关配置
  #RabbitMQ 服务器地址 
  spring.rabbitmq.host=127.0.0.1
  #RabbitMQ 端口
  spring.rabbitmq.port=5672
  #RabbitMQ 用户
  spring.rabbitmq.username=xx
  #RabbitMQ 密码p.
  spring.rabbitmq.password=xxx
  #是否确认发送的消息已经被消费
  spring.rabbitmq.publisher-confirms=true
  ```


### 3 Kafka

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-extends-mq-spring-kafka</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- spring扫描

  ```xml
  <!-- 非springboot项目须扫描该包 -->
  <context:component-scan base-package="cn.tomsnail.snail.ext.mq.kafka"/>
  ```

- 使用

  **发送**

  ```java
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
  ```

  **接收**

  ```java
  @Component
  public class KafkaReceiverService {
  	
  	@KafkaListener(topics = {"test"}, groupId = "receiver")
      public void registryReceiver(ConsumerRecord<Integer, String> integerStringConsumerRecords) {
          System.out.println("接收到Kafka消息："+integerStringConsumerRecords.value());
      }
  }
  ```

  

- 配置

  其他配置参考spring-kafka

  ```properties
  #kafka默认消费者配置
  spring.kafka.consumer.bootstrap-servers=127.0.0.1:8457
  spring.kafka.consumer.enable-auto-commit=false
  spring.kafka.consumer.auto-offset-reset=earliest
  #kafka默认生产者配置
  spring.kafka.producer.bootstrap-servers=127.0.0.1:8457
  spring.kafka.producer.acks=-1
  spring.kafka.client-id=kafka-producer
  spring.kafka.producer.batch-size=5
  ```