# 代码生成

- 引用

  ```xml
  <dependency>
      <groupId>cn.tomsnail.snail</groupId>
      <artifactId>snail-core-gen</artifactId>
      <version>${revision}</version>
  </dependency>
  ```

- 配置项

  ```properties
  gen.xml.filepath=
  gen.xml.template.path=
  
  gen.jdbc.driver=oracle.jdbc.driver.OracleDriver
  gen.jdbc.url=jdbc:oracle:thin:@192.168.169.159:1521:orcl
  gen.jdbc.username=****
  gen.jdbc.password=****
  gen.jdbc.tables=bs_enterprise_info,bs_enterprise_messenger
  gen.class.objects=BsEnterpriseInfo,BsEnterpriseMessenger
  gen.class.packageName=cn.tomsnail.objsql.gen
  gen.class.moduleName=test
  gen.class.version=0.0.1-SNAPSHOT
  gen.class.author=yangsong
  
  gen.pom=true
  gem.pom.modules.muliti=false
  gen.pom.parent.groupId=com.tomsnail
  gen.pom.parent.artifactId=gen-test
  gen.pom.parent.version=0.0.1-SNAPSHOT
  gen.pom.groupId=com.tomsnail
  gen.pom.artifactId=gentest
  gen.pom.version=0.0.1-SNAPSHOT
  
  #jdbc mybatis
  gen.dao=true
  gen.dao.jdbctype=mybatis
  gen.dao.db=mysql
  #UPPER\LOWER\Hump
  gen.dao.feild.type=UPPER
  gen.dao.service=true
  gen.dao.page=true
  gen.dao.vo=true
  gen.dao.dubbo=true
  
  
  gen.api.dao.service=true
  gen.api.service.dubbo=true
  
  
  gen.service=true
  gen.service.dubbo=true
  
  gen.http=true
  gen.http.dubbox=true
  gen.http.springboot=true
  
  gen.resource.java=true
  gen.resource.test=true
  gen.resource.system=true
  
  gen.junit=true
  gen.junit.service=true
  gen.junit.http=true
  gen.junit.dao=true
  gen.junit.dao.service=true
  ```

- 生成

  ```shell
  mvn exec:java -Dexec.mainClass="cn.tomsnail.core.gen.GenMain"
  ```

  