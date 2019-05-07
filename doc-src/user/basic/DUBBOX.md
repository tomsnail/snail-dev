# 发布基于Dubbo的HTTP服务

### 1 引用

```xml
<dependency>
   <groupId>cn.tomsnail.snail</groupId>
   <artifactId>snail-core-framework-dubbox</artifactId>
   <version>${reverion}</version>
</dependency>
```

### 2 接口定义

```java
public interface DemoFacade {

    public ResultData testGet(String no);

    public ResultData testPost(RequestData requestData);

}
```

### 3 Spring配置

```xml
<!-- dubbo 注册中心地址 -->
<dubbo:registry id="zk" protocol="zookeeper" address="${zk.address}" />
<!-- dubbo 协议配置 -->
<dubbo:protocol name="rest" port="${dubbo.server.port}" server="tomcat"/>
<!-- dubbo 注解扫描包 -->
<dubbo:annotation package="cn.tomsnail.snail.example.core.framework.dubbox" />
```

### 4 接口实现

```java
@Path("test")
@Service
public class DemoFacadeImpl implements DemoFacade{
	
	
	@Reference
	private DemoService demoService;

    @GET
    @Path("get/{no}")
    @Produces({MediaType.APPLICATION_JSON})
    @LogPoint
    public ResultData testGet(@PathParam("no")String no) {
        ResultData resultData = new ResultData();
        resultData.putBody("no",demoService.hello(no));
        return resultData;
    }

    @POST
    @Path("post")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public ResultData testPost(RequestData requestData) {
        return ResultData.createMapResult("timestamp",System.currentTimeMillis());
    }

}
```

### 5 配置项

```properties
#框架启动类型 dubbo/springboot  默认dubbo
framework.boot=dubbo

#以下内容是spring属性定义变量，依据spring内容特异化
#dubbo服务端口
dubbo.server.port = 20881
#zookeeper服务地址
zk.address=127.0.0.1:2181
```

### 6 启动

```bash
mvn exec:java -Dexec.mainClass="cn.tomsnail.snail.core.starter.AppMain"
```