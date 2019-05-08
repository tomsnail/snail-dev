# 参数验证与转换

## 1 参数转换

### 

## 2 参数验证

### 2.1 Dubbo

#### 2.1.1 使用hibernate-validator

```java
public class ModelObject implements Serializable {

    @NotNull
    private String id;

    private String name;

    private String age;

    private String desc;

}
```

```java
public interface DemoService {

    public String hello(@NotNull String name);

    public String vtest(ModelObject modelObject);

}
```

开启验证：validation = "true"

```java
@Service(protocol = "dubbo",registry = "zk",validation = "true",retries = 1)
public class DemoServiceImpl implements DemoService {
    
}
```

#### 2.1.2 使用snail-validator

*dubbo服务不推荐使用snail-validator*

### 2.2 Dubbox

#### 2.2.1 使用snail-validator

- 扫描

  ```xml
  <context:component-scan base-package="cn.tomsnail.snail.core.framework.validator"/>
  ```

- 使用

  对象验证，直接使用注解@SnailValidator

  ```java
  	@POST
      @Path("post")
      @Consumes({MediaType.APPLICATION_JSON})
      @Produces({MediaType.APPLICATION_JSON})
      @LogPoint
      @SnailValidator
      public ResultData<ModelObject> testPost(RequestData<ModelObject> requestData) {
          ResultData<ModelObject> modelObjectResultData = new ResultData<>();
          modelObjectResultData.setBody(requestData.getBody());
          return modelObjectResultData;
      }
  ```

  Map转换验证

  使用注解@SnailValidator，参数mapClass是要转换的对象

  ```java
      @POST
      @Path("post1")
      @Consumes({MediaType.APPLICATION_JSON})
      @Produces({MediaType.APPLICATION_JSON})
      @LogPoint
      @SnailValidator(mapClass = Vo.class)
      public ResultData<Map<String, Object>> testPost1(RequestData<Map<String, Object>> requestData) {
          Vo modelObject = (Vo) requestData.getTargetObj();
          logger.info("{}",modelObject);
          ResultData<Map<String, Object>> modelObjectResultData = new ResultData<>();
          modelObjectResultData.putBody("t",modelObject);
          return modelObjectResultData;
      }
  ```

  Vo,使用BeanValidator和FieldValidator注解，并且可以使用hibernate-validator注解

  ```java
  @BeanValidator
  public class Vo extends BaseVo<Mo> {
  
      @NotNull
      @FieldValidator(mapperName = "ID",onlyToBean = false)
      private String id;
  
      public String getId() {
          return id;
      }
  
      public void setId(String id) {
          this.id = id;
      }
  
  
  }
  ```

- 配置项

  ```properties
  #默认true
  system.valid=true
  ```

### 2.3 SpringBoot

同Dubbox

*也可使用spring-boot-starter-validation*