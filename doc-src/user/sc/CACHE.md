# 缓存

### 1 说明

*工程中使用ehcache本地缓存和redis分布式缓存。*

*ehcache提供ehcache和ehcache3，建议使用ehcache3。*

*J2Cache也使用ehcache3和redis*

### 2 ICache

#### 2.1 ehcache

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-e3-cache-ehcache</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- 使用

  ```java
  @RestController
  @RequestMapping("/ehcache")
  @SnailCache
  public class SpringBootEhcache {
  
  		 @CacheConfig(cachedType="EHCACHE",name="test")
  		 ICache ehcache;
      
  	     @LogPoint
  	     @RequestMapping(value = "put/{name}/{value}", method = RequestMethod.GET)
  	     public String put(@PathVariable("name") String name,@PathVariable("value") String value) {
  	    	 ehcache.set(name, value);
  	    	 return "OK";
  	     }
  	     
  	     @LogPoint
  	     @RequestMapping(value = "pute/{name}/{value}", method = RequestMethod.GET)
  	     public String putExpire(@PathVariable("name") String name,@PathVariable("value") String value) {
  	    	 ehcache.set(name, value, 3);
  	    	 return "OK";
  	     }
  	     
  	     
  	     @LogPoint
  	     @RequestMapping(value = "get/{name}", method = RequestMethod.GET)
  	     public String get(@PathVariable("name") String name) {
  	        return  String.valueOf(ehcache.get(name));
  	     }
  
  	
  }
  ```

  **在要使用的缓存的类上添加@SnailCache注解。**

  CacheConfig相关属性，所有属性都可以通过config配置文件读取（${xxxx}）：

  ```java
  public @interface CacheConfig {
  
  	/**
  	 *        url链接
  	 */
  	public String url() default "";
  	
  	/**
  	 *  	 端口
  	 */
  	public String port() default "0";
  	
  	/**
  	 *        缓存类型，默认为本地缓存 LOCAL,EHCACHE,EHCACHE3,REDIS,J2CACHE
  	 */
  	public CacheType cacheType() default CacheType.LOCAL;
  	
  	/**
  	 *        缓存类型，默认为本地缓存 LOCAL,EHCACHE,EHCACHE3,REDIS,J2CACHE
  	 */
  	public String cachedType() default "";
  	
  	/**
  	 *        缓存名称
  	 */
  	public String name() default "";
  	
  	/**
  	 *        缓存过期时间
  	 */
  	public String expire() default "10000";
  	
  	/**
  	 *        缓存超时时间
  	 */
  	public String timeout() default "10000";
  	
  	/**
  	 *        缓存的用户名，主要用于redis
  	 */
  	public String username() default "";
  	
  	/**
  	 *        缓存的密码，主要用于redis
  	 */
  	public String password() default "";
  	
  }
  ```

  ​	

- 配置

  ```properties
  #当CacheConfig的cachedType为EHCACHE,并且url为空时有效
  system.cache.ehcache.url=ehcache.xml
  ```

#### 2.2 ehcache3

- maven

  **注意**：ehcache和ehcache3的引用不能同时存在，ehcache中使用的class有版本问题

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-e3-cache-ehcache3</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- 使用

  同ehcache.

  ```java
  @CacheConfig(cachedType="EHCACHE3",name="test")
  ICache ehcache;
  ```

- 配置

  ```properties
  #当CacheConfig的cachedType为EHCACHE3,并且url为空时有效
  system.cache.ehcache3.url=ehcache3.xml
  ```

#### 2.3 redis

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-e3-cache-redis</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- 使用

  同ehcache和ehcach3

  ```java
  @CacheConfig(cachedType="REDIS",name="test",url="192.168.169.170",port="6379")
  ICache ehcache;
  ```

- 配置

  > CacheConfig相关属性，所有属性都可以通过config配置文件读取（${xxxx}）

### 3 J2Cache

​	**J2Cache是两级缓存框架，https://gitee.com/ld/J2Cache**

- maven

   ```xml
   <dependency>
       <groupId>cn.tomsnail.snail</groupId>
       <artifactId>snail-e3-cache-j2cache</artifactId>
       <version>${revision}</version>
   </dependency>
   ```

- 使用

   ```java
   @CacheConfig(cachedType="J2CACHE")
   ICache ehcache;
   ```

- 配置
  配置详细解释 https://gitee.com/ld/J2Cache/blob/master/core/resources/j2cache.properties

  ```properties
  #通过redis的订阅发布功能 进行一二级缓存数据的更新
  system.cache.j2cache.broadcast = redis
  #一级缓存 建议使用ehcache3
  system.cache.j2cache.L1.provider_class = ehcache3
  #ehcache3的配置文件
  system.cache.j2cache.ehcache3.configXml=ehcache3.xml
  #none 表示不启用2级缓存 redis
  system.cache.j2cache.L2.provider_class = redis
  system.cache.j2cache.L2.config_section = redis
  #过期时间是否同步
  system.cache.j2cache.sync_ttl_to_redis = true
  #空对象处理
  system.cache.j2cache.default_cache_null_object = true
  #数据序列化方式
  system.cache.j2cache.serialization = json
  #二级缓存redis配置
  system.cache.j2cache.redis.mode = single
  system.cache.j2cache.redis.storage = generic
  system.cache.j2cache.redis.channel = j2cache
  system.cache.j2cache.redis.channel.host =
  system.cache.j2cache.redis.cluster_name = j2cache
  system.cache.j2cache.redis.namespace =
  system.cache.j2cache.redis.hosts = 192.168.169.170:6379
  system.cache.j2cache.redis.timeout = 2000
  system.cache.j2cache.redis.password =
  system.cache.j2cache.redis.database = 0
  system.cache.j2cache.redis.maxTotal = 100
  system.cache.j2cache.redis.maxIdle = 10
  system.cache.j2cache.redis.maxWaitMillis = 5000
  system.cache.j2cache.redis.minEvictableIdleTimeMillis = 60000
  system.cache.j2cache.redis.minIdle = 1
  system.cache.j2cache.redis.numTestsPerEvictionRun = 10
  system.cache.j2cache.redis.lifo = false
  system.cache.j2cache.redis.softMinEvictableIdleTimeMillis = 10
  system.cache.j2cache.redis.testOnBorrow = true
  system.cache.j2cache.redis.testOnReturn = false
  system.cache.j2cache.redis.testWhileIdle = true
  system.cache.j2cache.redis.timeBetweenEvictionRunsMillis = 300000
  system.cache.j2cache.redis.blockWhenExhausted = false
  system.cache.j2cache.redis.jmxEnabled = false
  ```

  

### 4 Spring Cache

使用J2Cache的spring-boot2-starter模块，https://gitee.com/ld/J2Cache/tree/master/modules/spring-boot2-starter

- maven

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-e3-cache-springboot</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- 配置扫描

  ```xml
  <context:component-scan base-package="net.oschina.j2cache.autoconfigure"/>
  ```

- Spring Cache使用

  ```java
  @Service
  public class CacheService {
  
      private final AtomicInteger num = new AtomicInteger(0);
  
      @Cacheable(cacheNames="test")
      public Integer getNum() {
          return num.incrementAndGet();
      }
  
      @Cacheable(cacheNames="testBean")
      public CacheBean testBean() {
          CacheBean bean = new CacheBean();
          bean.setNum(num.incrementAndGet());
          return bean;
      }
      
      
      @CachePut(cacheNames="testBean")
      public CacheBean putBean() {
          CacheBean bean = new CacheBean();
          bean.setNum(num.incrementAndGet());
          return bean;
      }
  
      @CacheEvict(cacheNames={"testBean"})
      public void evict() {
  
      }
  
      public void reset() {
          num.set(0);
      }
  
  }
  ```

  Cacheable：缓存数据，如果有缓存，则不执行

  CacheEvict：清空数据

  CachePut：缓存数据，每次都执行，并把数据放入缓存

- CacheChannel使用

  ```java
  @Autowired
  private CacheChannel cacheChannel;
  
  //设置缓存
  cacheChannel.set("default",name,value);
  
  //获得缓存
  cacheChannel.get("default",name)
  ```

  

- 配置项

  其他配置参照J2Cache的配置

  ```properties
  #是否使用springcache，关闭后只能使用CacheChannel
  system.cache.j2cache.open-spring-cache=true
  #spring cache类型
  system.cache.j2cache.spring.cache.type=GENERIC
  #是否允许空值
  system.cache.j2cache.allow-null-values=true
  #缓存同步清除方式
  system.cache.j2cache.cache-clean-mode=passive
  #redis客户端
  system.cache.j2cache.redis-client=jedis
  #是否开启二级缓存
  system.cache.j2cache.l2-cache-open=true
  #缓存同步广播方式，在单独的J2Cache中是reids
  system.cache.j2cache.broadcast = net.oschina.j2cache.cache.support.redis.SpringRedisPubSubPolicy
  #一级缓存
  system.cache.j2cache.L1.provider_class = ehcache3
  system.cache.j2cache.ehcache3.configXml=ehcache3.xml
  #二级缓存，在单独的J2Cache中是redis
  system.cache.j2cache.L2.provider_class = net.oschina.j2cache.cache.support.redis.SpringRedisProvider
  system.cache.j2cache.L2.config_section=redis
  ```

  



