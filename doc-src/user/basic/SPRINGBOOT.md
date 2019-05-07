# 发布SpringBoot服务

### 1 引用

```xml
<dependency>
   <groupId>cn.tomsnail.snail</groupId>
   <artifactId>snail-core-framework-dubbo-springboot</artifactId>
   <version>${reverion}</version>
</dependency>
```

### 2 接口定义

```java
无
```

### 3 Spring配置

扫描spring-boot-rest包：

```xml
<context:component-scan base-package="cn.tomsnail.snail.example.core.framework.springboot"/>
```

引用Dubbo服务配置如下：

```xml
<!-- dubbo application配置 -->
<dubbo:application name="snail-example-core-framework-dubbo-annotation-server" >
    <dubbo:parameter key="qos.enable" value="false"/>
    <dubbo:parameter key="qos.accept.foreign.ip" value="false"/>
    <dubbo:parameter key="qos.port" value="33333"/>
</dubbo:application>

<!-- dubbo 注册中心地址 -->
<dubbo:registry id="zk" protocol="zookeeper" address="${zk.address}" />
<dubbo:annotation package="cn.tomsnail.snail.example.core.framework.springboot" />

```

### 4 实现

遵循SpringBoot注解使用方式

```java
@RestController
@RequestMapping("/springboot")
public class SpringBootRest {
	

		@Reference
		private DemoService demoService;
	    
	     @LogPoint
	     @RequestMapping(value = "/{name}", method = RequestMethod.GET)
	     public String sayHello(@PathVariable("name") String name) {
	         return demoService.hello(name);
	     }
	
}
```

### 5 配置项

```properties
#框架http服务类型，默认dubbo
framework.boot=springboot
#springboot端口配置，在本文件中对springboot进行配置，完全遵从springboot标准
server.port = 8081
#zookeeper注册中心地址，本示例须先启动snail-example-core-framework-dubbo的dubbo服务
zk.address=127.0.0.1:2181
```

### 6 启动

```bash
mvn exec:java -Dexec.mainClass="cn.tomsnail.snail.core.starter.AppMain"
```