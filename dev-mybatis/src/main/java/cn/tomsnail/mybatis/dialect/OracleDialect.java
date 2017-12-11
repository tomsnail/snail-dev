/*    */ package cn.tomsnail.mybatis.dialect;
/*    */ 
/*    */ public class OracleDialect extends Dialect
/*    */ {
/*    */   public String getLimitString(String sql, int offset, int limit)
/*    */   {
/* 12 */     sql = sql.trim();
/* 13 */     boolean isForUpdate = false;
/* 14 */     if (sql.toLowerCase().endsWith(" for update")) {
/* 15 */       sql = sql.substring(0, sql.length() - 11);
/* 16 */       isForUpdate = true;
/*    */     }
/*    */ 
/* 19 */     StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
/*    */ 
/* 21 */     pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
/*    */ 
/* 23 */     pagingSelect.append(sql);
/*    */ 
/* 25 */     pagingSelect.append(" ) row_ ) where rownum_ > " + offset + " and rownum_ <= " + (offset + limit));
/*    */ 
/* 27 */     if (isForUpdate) {
/* 28 */       pagingSelect.append(" for update");
/*    */     }
/*    */ 
/* 31 */     return pagingSelect.toString();
/*    */   }
/*    */ 
/*    */   public String getCountString(String sql)
/*    */   {
/* 37 */     return null;
/*    */   }
/*    */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.OracleDialect
 * JD-Core Version:    0.5.4
 */