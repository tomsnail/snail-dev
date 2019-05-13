# 安全

## 一 简单安全

### 1 Dubbo

*遵循Dubbo的安全技术和内网使用*

### 2 引入

```xml
 <dependency>
     <groupId>cn.tomsnail.snail</groupId>
     <artifactId>snail-extends-security-authority</artifactId>
     <version>${revision}</version>
</dependency>
```

### 3 Dubbox

```xml
<context:component-scan base-package="cn.tomsnail.snail.ext.security.authority.filter.dubbox"/>
```

### 4 Spring Boot

```xml
<context:component-scan base-package="cn.tomsnail.snail.ext.security.authority.filter.springboot"/>
```

### 5 配置项

```properties
#是否启用安全过滤，默认true
system.security.filter=true
#需要验证的url，空时全部需要验证，不为空时只针对配置的url进行验证
system.security.path=
#不需要验证的url，空时不作用，不为空时只针对配置的url不进行验证
system.security.path.excludes=/springboot/post,/springboot/post1
#是否添加附加信息
system.security.additional=true
#附加信息在url中的参数名称
system.core.http.add.flags=T,K
#附加信息在app中获取的key：BaseContextManager.get("t")
system.core.http.add.keys=t,k
#通过SNAIL安全过滤后用户UUID的参数名称，默认USER_UUID
system.core.http.user.flag=USER_UUID
#通过SNAIL安全过滤后系统编码的参数名称，默认SYSTEM_CODE
system.core.http.system.flag=SYSTEM_CODE
#SIMPLE 简单header验签方式   JWT JWT方式，其他或者空则不生效
system.security.type=SIMPLE

#JWT方式在header中获取的字段名，默认authorization
system.security.jwt.header=authorization
#JWT方式的秘钥
system.security.jwt.secret=MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=
#JWT方式的签名方法
system.security.jwt.alg=HmacSHA256

#SIMPLE方式的秘钥
system.security.simple.token=1qaz2wsx3EDC4RFV!@#
#SIMPLE方式在header中获取的签名字段名，默认snail-signature
system.security.simple.sign=snail-signature
#SIMPLE方式在header中获取的时间戳，默认snail-timestamp
system.security.simple.timestamp=snail-timestamp
```



## 二 Spring Security

## 三 Spring Shiro