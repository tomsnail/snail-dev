/*    */ package cn.tomsnail.snail.core.ds.mybatis.dialect;
/*    */ 
/*    */ public class MSDialect extends Dialect
/*    */ {
/*    */   protected static final String SQL_END_DELIMITER = ";";
/*    */ 
/*    */   public String getLimitString(String sql, int offset, int limit)
/*    */   {
/* 15 */     return MSPageHepler.getLimitString(sql, offset, limit);
/*    */   }
/*    */ 
/*    */   public String getCountString(String sql)
/*    */   {
/* 20 */     return MSPageHepler.getCountString(sql);
/*    */   }
/*    */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.MSDialect
 * JD-Core Version:    0.5.4
 */