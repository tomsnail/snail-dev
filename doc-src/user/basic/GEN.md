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
    #freemarks config 文件路径
    gen.xml.filepath=

    #freemarks 模板 文件路径
    gen.xml.template.path=

    #是否替换已有文件
    gen.replace=false

    #数据库配置
    gen.jdbc.driver=oracle.jdbc.driver.OracleDriver
    gen.jdbc.url=jdbc:oracle:thin:@192.168.169.159:1521:orcl
    gen.jdbc.username=****
    gen.jdbc.password=****
    #数据库中的表，* 表示所有表
    gen.jdbc.tables=bs_enterprise_info,bs_enterprise_messenger
    #生成对象的名称
    gen.class.objects=BsEnterpriseInfo,BsEnterpriseMessenger
    #生成包路径
    gen.class.packageName=cn.tomsnail.example.gen
    #模块名称
    gen.class.moduleName=ex
    #类版本
    gen.class.version=0.0.1-SNAPSHOT
    #作者
    gen.class.author=yangsong
    
    #是否生成pom文件，及其相关信息
    gen.pom=true
    gem.pom.modules.muliti=false
    gen.pom.parent.groupId=cn.tomsnail.snail
    gen.pom.parent.artifactId=snail-dao-springboot-pom
    gen.pom.parent.version=1.0-SNAPSHOT
    gen.pom.groupId=cn.tomsnail.example1
    gen.pom.artifactId=gen-example
    gen.pom.version=0.0.1-SNAPSHOT
    gen.pom.package=true
    #是否生成dao文件，及其相关信息
    gen.dao=true
    gen.dao.jdbctype=mybatis
    gen.dao.db=mysql
    gen.dao.feild.type=UPPER
    gen.dao.service=true
    #gen.dao.page=true
    gen.dao.vo=true
    gen.dao.dubbo=true
    #是否web api文件，及其相关信息
    gen.http=true
    gen.http.dubbox=false
    gen.http.springboot=true
    gen.http.springboot.dubbo=true
    #是否生成resouce文件
    gen.resource.main=true
    gen.resource.test=true
    gen.resource.system=true
  ```

- 生成

  ```shell
  mvn exec:java -Dexec.mainClass="cn.tomsnail.core.gen.GenMain"
  ```

  