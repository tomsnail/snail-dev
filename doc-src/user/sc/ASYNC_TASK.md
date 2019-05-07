# 异步与任务

### 1 Spring 异步调用与任务

- maven

  ```xml
  <!--引用基础framework都自带该功能-->
  ```

- spring扫描

  ```xml
  <!-- 异步线程 池,用于开启spring定时任务和异步调用-->
  <context:component-scan base-package="cn.tomsnail.snail.core.service.spring.core.async"/>
  ```

  

- 异步调用

  ```java
  @Component
  public class SpringAsyncService {
  	
  	@Async
  	public void asyncCall() {
  		System.out.println("asyncCall start......");
  		try {
  			Thread.sleep(3000);
  		} catch (InterruptedException e) {
  			e.printStackTrace();
  		}
  		System.out.println("asyncCall end......");
  	}
  
  }
  ```

  注意：Async注解与PostConstruct注解不能一起使用，包括在PostConstruct中调用Async

- 任务

  ```java
  @Component
  public class SpringScheduledService {
  	
  	@Scheduled(fixedDelayString="${spring.schedule.test.fiexd.delay}")
  	@Async
  	public void job() {
  		System.out.println("do job!!");
  	}
  
  }
  
  ```

  

- 配置项

  ```properties
  #异步线程池核心数
  system.spring.async.executor.core.size=10
  #异步线程池最大数
  system.spring.async.executor.max.size=30
  #队列长度
  system.spring.async.executor.queue.capacity=1000
  
  #任务调度周期，自定义属性
  spring.schedule.test.fiexd.delay=2000
  ```

### 2 任务执行记录

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-extends-task-core</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

  

- spring扫描

  ```xml
  <context:component-scan base-package="cn.tomsnail.snail.ext.task.spring"/>
  ```

- 使用

  在任务方法上添加Task注解

  ```java
  @Component
  public class SpringScheduledService {
  	
  	@Scheduled(fixedDelayString="${spring.schedule.test.fiexd.delay}")
  	@Async
  	@Task
  	public void job() {
  		System.out.println("do job!!");
  	}
  
  }
  ```

- 配置项

  ```properties
  
  ```

  

### 3 分布式任务-ElasticJob

#### 3.1 基础配置

##### maven

```xml
<dependency>
    <groupId>cn.tomsnail.snail</groupId>
    <artifactId>snail-e3-task-elastic-job</artifactId>
    <version>${revision}</version>
</dependency>
```

##### spring配置

```xml
<context:component-scan base-package="cn.tomsnail.snail.e3.task.elastic.job"/>
```

##### 使用

```java
@Component
@SpringElasticJob(jobName="TestJob1", type=JobType.single,cron="${test.cron}")
public class MyJob implements SimpleJob{

	@Override
	public void execute(ExecutionContext executionContext) {
		System.out.println("MyJob");
	}

}
```

jobName：任务名称

type：任务类型，single、multiple

cron：任务表达式

##### 配置项

```properties
#zookeeper地址
system.elastic.job.server-lists=192.168.169.170:2181
#任务命名空间,zk根目录
system.elastic.job.namespace=SnailJob
#zk的sleep时间
system.elastic.job.base-sleep-time-milliseconds=1000
#zk的sleep时间
system.elastic.job.max-sleep-time-milliseconds=3000
#zk的重试次数
system.elastic.job.max-retries=3

#每隔5秒执行一次
test.cron=0/5 * * * * ?
```

#### 3.2 多任务配置

##### 使用

```java
@Component
@SpringElasticJob(jobName="TestJob", type=JobType.multiple,jobDataSource="mysqlDataSource",jobSQL="select JOB_NAME,CRON,PARAMS from t_task t where del_flag = '0' ")
public class MyJob implements SimpleJob{

	@Override
	public void execute(ExecutionContext executionContext) {
		System.out.println("MyJob");
	}

}
```

将通过select JOB_NAME,CRON,PARAMS from forward_task t where del_flag = '0'  查询数据后，创建基于本类的多个不同周期和参数的任务

jobDataSource，数据源名称

#### 3.3 任务控制

参考elastic-job控制台或者其API进行操作

#### 3.4 任务日志跟踪

##### 数据库

```properties
#是否通过数据库记录日志，默认为false
system.elastic.job.event-db=true
#数据源名称
system.elastic.job.event-trace-rdb-data-source=xxx
```

数据库相关参考http://elasticjob.io/docs/elastic-job-lite/02-guide/event-trace/

##### 消息(推荐)

[引用Kafka](./MQ.md)

```properties
#是否通过消息发送任务日志，默认为false
system.elastic.job.event-mq=true
#消息类型，kafka,目前只支持kafka
system.elastic.job.event-mq-type=kafka
#kafka队列名称
system.elastic.job.event-mq-kafka-queue=Ealstic_JOB
```

**注意：消息和数据库不能同时存在，同时配置时，以MQ为主**