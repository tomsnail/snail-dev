# 配置管理

### 1.开启Spring属性加载

```xml
<!-- spring 属性配置插件，用于spring相关属性(如${zk.address})从config.properties中获取值 -->
<context:component-scan base-package="cn.tomsnail.snail.core.config.client.plugin"/>
```

### 2.读取本地配置

```java
ConfigHelp.getInstance("config").getLocalConfig("system.log.level", "1")
```

### 3.使用配置工厂

```java
String c = ConfigClientFactory.getInstance().getConfigClient().getConfig("xxxx", "");
```

> 配置工厂使用配置顺序：本地文件>Zookeeper>Redis>数据库

### 4.动态配置

#### 4.1扫描

```xml
<context:component-scan base-package="cn.tomsnail.snail.core.config.client.spring"/>
```

#### 4.2示例

```java
@ConfigListener
@Component
public class ConfigClass {

	public static String CONF_KEY = "value1";
	
}
```

```java

public class ConfigClassTest extends BaseTest{
  
  public void testDynaimsConfig(){
    logger.info(ConfigClass.CONF_KEY);
  }
  
}
```

### 5.配置项

```properties
system.config.zookeeper=false
system.config.zookeeper.address=127.0.0.1:2181
system.config.zookeeper.root=/tomsnail/config/root

system.config.redis=false
system.config.redis.address=127.0.0.1:2181
system.config.redis.mapkey=tomsnail:config

system.config.db=false
system.config.db.type=JdbcTemplate
system.config.db.datasource=
system.config.db.driver=com.mysql.jdbc.Driver
system.config.db.url=jdbc:mysql://localhost:3306/databasename
system.config.db.username=username
system.config.db.password=password
system.config.db.kv_sql=select value as keyValue from ts_config where name = ${key}

```

