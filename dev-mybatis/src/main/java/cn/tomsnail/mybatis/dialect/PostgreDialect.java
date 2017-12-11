/*    */ package cn.tomsnail.mybatis.dialect;
/*    */ 
/*    */ public class PostgreDialect extends Dialect
/*    */ {
/*    */   protected static final String SQL_END_DELIMITER = ";";
/*    */ 
/*    */   public String getLimitString(String sql, int offset, int limit)
/*    */   {
/* 14 */     return PostgrePageHepler.getLimitString(sql, offset, limit);
/*    */   }
/*    */ 
/*    */   public String getCountString(String sql)
/*    */   {
/* 19 */     return PostgrePageHepler.getCountString(sql);
/*    */   }
/*    */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.PostgreDialect
 * JD-Core Version:    0.5.4
 */