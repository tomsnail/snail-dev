/*    */ package cn.tomsnail.snail.core.ds.mybatis.dialect;

/**
 * The type My sql 5 dialect.
 */
/*    */
/*    */ public class MySql5Dialect extends Dialect
/*    */ {
    /**
     * The constant SQL_END_DELIMITER.
     */
    /*    */   protected static final String SQL_END_DELIMITER = ";";
/*    */ 
/*    */   public String getLimitString(String sql, int offset, int limit)
/*    */   {
/* 15 */     return MySql5PageHepler.getLimitString(sql, offset, limit);
/*    */   }
/*    */ 
/*    */   public String getCountString(String sql)
/*    */   {
/* 20 */     return MySql5PageHepler.getCountString(sql);
/*    */   }
/*    */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.MySql5Dialect
 * JD-Core Version:    0.5.4
 */