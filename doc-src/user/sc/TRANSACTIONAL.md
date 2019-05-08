# 事务

## 一 基础事务

- 继承BaseTransactionComponent

  ```java
  public class DemoServiceImpl extends BaseTransactionComponent{
      
  }
  ```

  

- 实现BaseTransactionService

  ```java
  public class DemoServiceImpl implements BaseTransactionService{
      
  }
  ```

  

- 使用注解Transactional

  ```java
      @Transactional
      public void test(){
  
          logger.info(" mysql1 {}", dispatchStrategyDao.get("1"));
          logger.info(" mysql2 {}", testDao.test());
      }
  ```

## 二 分布式事务

