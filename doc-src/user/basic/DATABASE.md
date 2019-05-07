# 数据库服务

## 一 基础使用

### 1 引用

```xml
<dependency>
    <groupId>cn.tomsnail.snail</groupId>
    <artifactId>snail-core-framework-dao</artifactId>
    <version>${revision}</version>
</dependency>
```

### 2 spring配置

```xml
<context:component-scan base-package="cn.tomsnail.snail.core.ds.mybatis.autoconfig"/>
```

### 3 配置项

```properties
#数据源类型
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
#druid相关配置
spring.datasource.druid.filters=stat
spring.datasource.druid.driver-class-name=${p.db.jdbc.driver}
spring.datasource.druid.url=${p.db.jdbc.url}
spring.datasource.druid.username=${p.db.jdbc.username}
spring.datasource.druid.password=${p.db.jdbc.password}
spring.datasource.druid.initial-size=${p.db.ds.initialSize}
spring.datasource.druid.min-idle=${p.db.ds.minIdle}
spring.datasource.druid.max-active=${p.db.ds.maxActive}
spring.datasource.druid.max-wait=${p.db.ds.maxWait}
spring.datasource.druid.time-between-eviction-runs-millis=${p.db.ds.timeBetweenEvictionRunsMillis}
spring.datasource.druid.min-evictable-idle-time-millis=${p.db.ds.minEvictableIdleTimeMillis}
spring.datasource.druid.validation-query=${p.db.ds.query}
spring.datasource.druid.test-while-idle=true
spring.datasource.druid.test-on-borrow=false
spring.datasource.druid.test-on-return=false
spring.datasource.druid.pool-prepared-statements=false
spring.datasource.druid.max-pool-prepared-statement-per-connection-size=20
#mybatis扫描mapper路径
mybatis.mapper-locations=classpath:cn/tomsnail/snail/example/core/framework/dao/*.xml
#mybatis扫描model路径
mybatis.type-aliases-package=cn.tomsnail.snail.example.core.framework.model
#mybatis扫描dao路径
mybatis.mapper-scanners=cn.tomsnail.snail.example.core.framework.dao
```



**注意：当数据源不止一个或者需要使用RountingDataSource时，采用spring的XML配置方式**



## 二 SpringBoot

### 1 添加引用

```xml
<dependency>
    <groupId>cn.tomsnail.snail</groupId>
    <artifactId>snail-core-ds-springboot</artifactId>
    <version>${revision}</version>
</dependency>
```

### 2 spring扫描

```xml
<context:component-scan base-package="cn.tomsnail.snail.core.ds.springboot.mybatis"/>
```

### 3 配置项

同基础使用的配置项