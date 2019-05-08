# 参数验证与转换

## 1 参数转换

### 1.1 JSON转对象



### 1.2 对象转JSON



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



### 2.2 Dubbox

### 2.3 SpringBoot