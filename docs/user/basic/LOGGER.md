# 日志记录

### 1.开启日志扫描

```xml
<!-- 日志记录插件，须开启动态代理 -->
<context:component-scan base-package="cn.tomsnail.snail.core.log"/>
<aop:aspectj-autoproxy proxy-target-class="true"/>
```

### 2.日志注解

```java
@Component("demoService")
public class DemoServiceImpl implements DemoService {
    
    @Override
    @LogPoint
    public String hello(String name) {
        return "hello "+name;
    }
}
```

### 3.注解说明

```java
public @interface LogPoint {

	/**
	 *        级别,默认INFO级别，INFO,ERROR,DEBUG,NOLOG
	 */
	String level() default LogLevel.INFO;
	/**
	 *        日志记录的目的地,默认本地日志,LOG,CENTER,SYSOUT,CAT
	 */
	String target() default LogTarget.LOG;
	/**
	 *        描述,用于说明方法相关描述
	 */
	String desc() default "";
	/**
	 *        是否记录到全部日志系统
	 */
	boolean shared() default false;
	
}
```

### 4.配置项

```java
@Component("demoService")
public class DemoServiceImpl implements DemoService {
    
    @Override
    @LogPoint(level = "${demo.logger.level}")
    public String hello(String name) {
        return "hello "+name;
    }
}
```

> ${demo.logger.level} 将会引用config.properties中的配置,其他配置类同

### 5.全局配置

```properties
#是否记录日志，默认true
system.islogger=true
#日志记录级别 INFO 1,ERROR 0,DEBUG 2,NOLOG -1,B_ERROR 3
system.log.level=1

##############日志中心配置#################
#是否记录到日志中心
system.log.center=true
#日志中心配置，通过log4j2的Appender发送到kafka消息队列
#日志名称
system.log.center.LOG_NAME=DefaultCenterLogService
#日志格式
system.log.center.LAYOUT=%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n
#日志地址
system.log.center.address=127.0.0.1:8457
#日志地址，LogAddress如果没有，将使用address配置
system.log.center.LogAddress=127.0.0.1:8457
#log4j2的AppenderName
system.log.center.AppenderName=kafka
#kafka主题
system.log.center.topic=LOG_CENTER
##########################################

##################CAT配置#################
system.log.cat=true
##########################################

```

### 6.其他日志记录

```java
Logger logger = LoggerFactory.getLogger("");//使用org.slf4j.Logger

//使用日志帮助类
ILogger loggerHelper = new LoggerHelper();
//默认记录到日志文件
loggerHelper.info("logger point");
//记录到日志中心
loggerHelper.info("center logger point", LogTarget.CENTER);

```

