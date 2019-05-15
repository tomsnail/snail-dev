/*    */ package cn.tomsnail.snail.core.ds.mybatis.dialect;
/*    */ 
/*    */ import org.apache.ibatis.session.Configuration;

/**
 * The type Dialect factory.
 */
/*    */
/*    */ public class DialectFactory
/*    */ {
    /**
     * The constant dialectClass.
     */
    /* 13 */   public static String dialectClass = null;

    /**
     * Build dialect dialect.
     *
     * @param configuration the configuration
     * @return the dialect
     */
    /*    */
/*    */   public static Dialect buildDialect(Configuration configuration) {
/* 16 */     if (dialectClass == null) {
/* 17 */       synchronized (DialectFactory.class) {
/* 18 */         if (dialectClass == null) {
/* 19 */           dialectClass = configuration.getVariables().getProperty("dialectClass");
/*    */         }
/*    */       }
/*    */     }
/* 23 */     Dialect dialect = null;
/*    */     try {
/* 25 */       dialect = (Dialect)Class.forName(dialectClass).newInstance();
/*    */     } catch (Exception e) {
/* 27 */       e.printStackTrace();
/* 28 */       System.err.println("请检查 mybatis-config.xml 中  dialectClass 是否配置正确?");
/*    */     }
/* 30 */     return dialect;
/*    */   }
/*    */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.DialectFactory
 * JD-Core Version:    0.5.4
 */