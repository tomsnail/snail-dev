# 发布Dubbo服务

### 1 引用

#### 1.1 Maven仓库

#### 1.2 pom引用

```xml
<dependency>
   <groupId>cn.tomsnail.snail</groupId>
   <artifactId>snail-core-framework-dubbo</artifactId>
   <version>${last_verion}</version>
</dependency>
```

### 2 接口定义

```java
public interface DemoService {
    public String hello(String name);
}
```

### 3 XML

```xml
<!-- 日志记录插件，须开启动态代理 -->
<context:component-scan base-package="cn.tomsnail.snail.core.log"/>
<aop:aspectj-autoproxy proxy-target-class="true"/>

<context:component-scan base-package="cn.tomsnail.snail.example.core.framework.dubbo"/>

<dubbo:application name="snail-example-core-framework-dubbo-server" />  
<dubbo:registry id="zk" protocol="zookeeper" address="${zk.address}" />
<dubbo:protocol name="dubbo" port="${dubbo.server.port}" />
<dubbo:service ref="demoService" interface="cn.tomsnail.snail.example.core.framework.dubbo.DemoService" protocol="dubbo" registry="zk"/>
```

接口实现：

```java
package cn.tomsnail.snail.example.core.framework.dubbo;

import cn.tomsnail.snail.core.log.annotation.LogPoint;
import org.springframework.stereotype.Component;

@Component("demoService")
public class DemoServiceImpl extends BaseService implements DemoService {

    @Override
    @LogPoint
    public String hello(String name) {
        return "hello "+name;
    }
}
```

### 4 注解

```xml
<!-- 日志记录插件，须开启动态代理 -->
<context:component-scan base-package="cn.tomsnail.snail.core.log"/>
<aop:aspectj-autoproxy proxy-target-class="true"/>
<!-- spring 属性配置插件，用于spring相关属性(如${zk.address})从config.properties中获取值 -->
<context:component-scan base-package="cn.tomsnail.snail.core.config.client.plugin"/>




<!-- dubbo application配置 -->
<dubbo:application name="snail-example-core-framework-dubbo-annotation-server" >
	<dubbo:parameter key="qos.enable" value="false"/>
	<dubbo:parameter key="qos.accept.foreign.ip" value="false"/>
	<dubbo:parameter key="qos.port" value="33333"/>
</dubbo:application>


<!-- dubbo 注册中心地址 -->
<dubbo:registry id="zk" protocol="zookeeper" address="${zk.address}" />
<!-- dubbo 协议配置 -->
<dubbo:protocol name="dubbo" port="${dubbo.server.port}" />
<!-- dubbo 注解扫描包 -->
<dubbo:annotation package="cn.tomsnail.snail.example.core.framework.dubbo.annotation.server" ></dubbo:annotation>
```

接口实现(使用dubbo服务注解Service)：

```java
package cn.tomsnail.snail.example.core.framework.dubbo.annotation.server;

import cn.tomsnail.snail.core.log.annotation.LogPoint;
import cn.tomsnail.snail.example.core.framework.dubbo.DemoService;
import com.alibaba.dubbo.config.annotation.Service;

@Service(protocol = "dubbo",registry = "zk")
public class DemoServiceImpl implements DemoService {

    @Override
    @LogPoint
    public String hello(String name) {
        return "hello "+name;
    }
}
```

### 5.内嵌ZooKeeper服务器

```xml
<!-- zookeepr 内嵌服务器 -->
<context:component-scan base-package="cn.tomsnail.snail.core.util.zookeeper.server"/>
```

### 6 配置

```properties
#框架启动类型 dubbo/springboot  默认dubbo
framework.boot=dubbo

#以下内容是spring属性定义变量，依据spring内容特异化
#dubbo服务端口
dubbo.server.port = 20881
#zookeeper服务地址
zk.address=127.0.0.1:2181
```

### 7 启动

```bash
mvn exec:java -Dexec.mainClass="cn.tomsnail.snail.core.starter.AppMain"
```

