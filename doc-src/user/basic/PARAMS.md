# 参数验证与转换

## 1 基础验证

```java
@BeanValidator
public class Vo extends BaseVo<Mo> {

    @NotNull
    @FieldValidator(mapperName = "ID",onlyToBean = false)
    private String id;

    
    @FieldValidator(mapperName = "NickName",onlyToBean = false,rules = {RuleType.NotNullValue,RuleType.LIMITMAX},values = {"","20"})
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
public @interface FieldValidator {

	   /**
		*        规则集合
		*/
	RuleType[] rules() default {};
	
	  /**
		*        验证类型
		*/
	String[] validTypes() default {};
	
	   /**
		*       规则所对应的值集合
		*/
	String[] values() default {};
	   /**
		*        map中key名
		*/
	String mapperName() default "";
	   /**
		*        是否只是转换为数据，不进行验证
		*/
	boolean onlyToBean() default false;
	   /**
		*        如果是date类型时的时间字符串格式
		*/
	String dateFormat() default "yyyy-MM-dd HH:mm:ss";
	   /**
		*        验证失败后，抛出的自定义错误信息
		*/
	String[] errorMsg() default {""};
	
}
```



## 1.1 Map数据验证

- 将Map转换为数据对象，并启用FileValidator

  ```java
  ValidatorFactory.getBeanValidator().getValidBean(requestData.getBody(),Vo.class);
  ```

- 将Map转换为数据对象，并启用FileValidator,如果存在Hibernate-Validator注解也一并验证，但须在对象类上使用BeanValidator注解

  ```java
  ValidatorFactory.getHibernateObjectValidator().getValidBean(requestData.getBody(),Vo.class);
  ```

- 获取Map中的ID属性 

  ```java
  ValidatorFactory.getStringValidator().getValidatorValue(requestData.getBody(),"ID");
  ```

- 获取Map中的ID属性，如果没有返回 自定义错误信息

  ```java
  ValidatorFactory.getIntegerValidator().getValidatorValue(requestData.getBody(),"ID","id不能为空");
  ```

- 获取Map中的ID属性，添加验证规则，如果没有返回 自定义错误信息

  ```java
  ValidatorFactory.getStringValidator().add(RuleType.NotNull).add(RuleType.LIMITMAX,20).getValidatorValue(requestData.getBody(),"ID");
  ```

## 1.2 对象验证

- Snail-Validator

  ```java
  ValidatorFactory.getBeanValidator().valid(vo);
  ```

- Hibernate-Validator,同时进行Snail-Validator验证

  ```java
    ValidatorFactory.getHibernateObjectValidator().valid(vo);
  ```

## 1.3 VO与MO

**Mo继承BaseModel，Vo继承BaseVo**

- Mo

  ```java
  public class Mo extends BaseModel {
  
      private String id;
  
      private String name;
  
      private String age;
  
      private String desc;
  
      public String getId() {
          return id;
      }
  
      public void setId(String id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getAge() {
          return age;
      }
  
      public void setAge(String age) {
          this.age = age;
      }
  
      public String getDesc() {
          return desc;
      }
  
      public void setDesc(String desc) {
          this.desc = desc;
      }
  }
  ```

- Vo

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

- Vo1

  ```java
  @BeanValidator
  public class Vo1 extends BaseVo<Mo> {
  
      @NotNull
      @FieldValidator(mapperName = "ID",onlyToBean = false)
      private String id;
  
      @FieldValidator(mapperName = "NickName",onlyToBean = false,rules = {RuleType.NotNullValue,RuleType.LIMITMAX},values = {"","20"})
      private String name;
  
  
  
      public String getId() {
          return id;
      }
  
      public void setId(String id) {
          this.id = id;
      }
  
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  }
  ```

- Vo转Mo

  ```java
  Mo mo1 = vo.getModel(Mo.class);
  Mo mo2 = vo1.getModel(Mo.class);
  ```

- Mo转Map,只会转换vo中的属性字段

  ```java
  Map map = vo.getMap(mo);
  ```


## 2 请求参数验证与转换

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

**注意：复杂数据对象须使用注解@ResponseBody和@RequestBody** 