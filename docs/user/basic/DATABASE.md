# 数据库

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
<context:component-scan base-package="cn.tomsnail.snail.core.ds.mybatis.autoconfig.single"/>
```

### 3 配置项

```properties
#数据源类型
spring.datasource.name=dataSource
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

## 三 JDBC

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-core-framework-dao</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- spring配置

  ```xml
  <context:component-scan base-package="cn.tomsnail.snail.core.ds.autoconfig.jdbc"/>
  ```

- 使用

  ```java
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Scheduled(fixedDelayString="20000")
  @Async
  public void jdbc(){
      logger.info(" jdbcTemplate execute");
      jdbcTemplate.execute("select 'x'");
  
  }
  ```

- 配置项

  ```properties
  #数据源类型
  spring.datasource.name=dataSource
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

  同样依赖数据源配置

## 四 多数据源

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-core-framework-dao</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- spring配置

  ```xml
  <context:component-scan base-package="cn.tomsnail.snail.core.ds.mybatis.autoconfig.multi"/>
  ```

- 使用

  ```java
  @Repository
  @DataSource("mysql1")
  public interface DispatchStrategyDao extends PageDao<DispatchStrategyDto> {
  	
  	public List<DispatchStrategyDto> findList(DispatchStrategyDto dispatchStrategy);
  	
  	public List<DispatchStrategyDto> findAllList();
  	
  	public DispatchStrategyDto get(String id);
  	
  	public int findPageCount(DispatchStrategyDto dispatchStrategy);
  	
  	public int insert(DispatchStrategyDto dispatchStrategy);
  	
  	public int delete(String id);
  	
  	public int update(DispatchStrategyDto dispatchStrategy);
  	
  }
  ```

  在dao上添加DataSource注解

- 配置项

  ```properties
  #数据源名称
  spring.datasource.name.router=dataSource
  #多数据源名称标识，匹配@DataSource中的值
  spring.datasource.name.router-name=mysql1,mysql2
  #多数据源路由类型WR、AUTO_WR、MS、MULTI_TYPE_DB、GROUP_MS、default
  spring.datasource.name.router-type=MULTI_TYPE_DB
  #默认数据源
  spring.datasource.name.router-default=mysql1
  
  #在基础数据源配置Key后加上@DataSource中的值
  spring.datasource.druid.filters.mysql1=stat
  spring.datasource.druid.driver-class-name.mysql1=${p.db.jdbc.driver}
  spring.datasource.druid.url.mysql1=${p.db.jdbc.url.mysql1}
  spring.datasource.druid.username.mysql1=${p.db.jdbc.username.mysql1}
  spring.datasource.druid.password.mysql1=${p.db.jdbc.password.mysql1}
  spring.datasource.druid.initial-size.mysql1=${p.db.ds.initialSize}
  spring.datasource.druid.min-idle.mysql1=${p.db.ds.minIdle}
  spring.datasource.druid.max-active.mysql1=${p.db.ds.maxActive}
  spring.datasource.druid.max-wait.mysql1=${p.db.ds.maxWait}
  spring.datasource.druid.time-between-eviction-runs-millis.mysql1=${p.db.ds.timeBetweenEvictionRunsMillis}
  spring.datasource.druid.min-evictable-idle-time-millis.mysql1=${p.db.ds.minEvictableIdleTimeMillis}
  spring.datasource.druid.validation-query.mysql1=${p.db.ds.query}
  spring.datasource.druid.test-while-idle.mysql1=true
  spring.datasource.druid.test-on-borrow.mysql1=false
  spring.datasource.druid.test-on-return.mysql1=false
  spring.datasource.druid.pool-prepared-statements.mysql1=false
  spring.datasource.druid.max-pool-prepared-statement-per-connection-size.mysql1=20
  
  ```

  **注意：多数据源时不能存在数据库事务**

## 五 其他数据库

- sqlite

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-e3-db-sqlite</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

  ```properties
  spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
  spring.datasource.druid.filters=stat
  spring.datasource.druid.driver-class-name=org.sqlite.JDBC
  spring.datasource.druid.url=jdbc:sqlite:/data/test.db
  spring.datasource.druid.username=
  spring.datasource.druid.password=
  spring.datasource.druid.validation-query=select 'x'
  spring.datasource.druid.test-while-idle=true
  spring.datasource.druid.test-on-borrow=false
  spring.datasource.druid.test-on-return=false
  spring.datasource.druid.pool-prepared-statements=false
  spring.datasource.druid.max-pool-prepared-statement-per-connection-size=20
   
  mybatis.mapper-locations=classpath:cn/tomsnail/snail/example/core/framework/dao/*.xml
  mybatis.type-aliases-package=cn.tomsnail.snail.example.core.framework.model
  mybatis.mapper-scanners=cn.tomsnail.snail.example.core.framework.dao
  ```

  

- h2

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-e3-db-h2</artifactId>
      <version>${revision}</version>
  </dependency>
  ```