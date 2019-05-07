# 锁

### 1 基础锁

#### 1.1 引用

```xml
<dependency>
    <groupId>cn.tomsnail.snail</groupId>
    <artifactId>snail-extends-lock</artifactId>
    <version>${revision}</version>
    <scope>provided</scope>
</dependency>
```

#### 1.2 Spring扫描

```xml
<context:component-scan base-package="cn.tomsnail.snail.ext.lock.spring"/>
```

#### 1.3 使用

```java
@Component
public class LockTest {

    @Lock(name = "lockt",target = LockTarget.ZOOKEEPER,url = "${url}")
    ILock lock;

    public void test(){
        lock.lock();
        System.out.println(System.currentTimeMillis());
        lock.unLock();
    }
}
```

target:ZOOKEEPER/REDIS/LOCAL

#### 1.4 配置项

```properties

```

