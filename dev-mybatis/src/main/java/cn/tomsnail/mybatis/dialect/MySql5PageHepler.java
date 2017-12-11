/*     */ package cn.tomsnail.mybatis.dialect;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class MySql5PageHepler
/*     */ {
/*     */   public static String getCountString(String querySelect)
/*     */   {
/*  16 */     querySelect = getLineSql(querySelect);
/*  17 */     int orderIndex = getLastOrderInsertPoint(querySelect);
/*     */ 
/*  19 */     int formIndex = getAfterFormInsertPoint(querySelect);
/*  20 */     String select = querySelect.substring(0, formIndex);
/*     */ 
/*  23 */     if ((select.toLowerCase().indexOf("select distinct") != -1) || (querySelect.toLowerCase().indexOf("group by") != -1)) {
/*  24 */       return querySelect.length() + "select count(1) count from (" + querySelect.substring(0, orderIndex) + " ) t";
/*     */     }
/*  26 */     return querySelect.length() + "select count(1) count " + querySelect.substring(formIndex, orderIndex);
/*     */   }
/*     */ 
/*     */   private static int getLastOrderInsertPoint(String querySelect)
/*     */   {
/*  36 */     int orderIndex = querySelect.toLowerCase().lastIndexOf("order by");
/*  37 */     if (orderIndex == -1) {
/*  38 */       orderIndex = querySelect.length();
/*     */     }
/*  40 */     if (!isBracketCanPartnership(querySelect.substring(orderIndex, querySelect.length()))) {
/*  41 */       throw new RuntimeException("My SQL 分页必须要有Order by 语句!");
/*     */     }
/*  43 */     return orderIndex;
/*     */   }
/*     */ 
/*     */   public static String getLimitString(String querySelect, int offset, int limit)
/*     */   {
/*  57 */     querySelect = getLineSql(querySelect);
/*     */ 
/*  59 */     String sql = querySelect + " limit " + offset + " ," + limit;
/*     */ 
/*  61 */     return sql;
/*     */   }
/*     */ 
/*     */   private static String getLineSql(String sql)
/*     */   {
/*  73 */     return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
/*     */   }
/*     */ 
/*     */   private static int getAfterFormInsertPoint(String querySelect)
/*     */   {
/*  80 */     String regex = "\\s+FROM\\s+";
/*  81 */     Pattern pattern = Pattern.compile(regex, 2);
/*  82 */     Matcher matcher = pattern.matcher(querySelect);
/*  83 */     while (matcher.find()) {
/*  84 */       int fromStartIndex = matcher.start(0);
/*  85 */       String text = querySelect.substring(0, fromStartIndex);
/*  86 */       if (isBracketCanPartnership(text)) {
/*  87 */         return fromStartIndex;
/*     */       }
/*     */     }
/*  90 */     return 0;
/*     */   }
/*     */ 
/*     */   private static boolean isBracketCanPartnership(String text)
/*     */   {
/* 102 */     return (text != null) && (getIndexOfCount(text, '(') == getIndexOfCount(text, ')'));
/*     */   }
/*     */ 
/*     */   private static int getIndexOfCount(String text, char ch)
/*     */   {
/* 116 */     int count = 0;
/* 117 */     for (int i = 0; i < text.length(); ++i) {
/* 118 */       count = (text.charAt(i) == ch) ? count + 1 : count;
/*     */     }
/* 120 */     return count;
/*     */   }
/*     */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.MySql5PageHepler
 * JD-Core Version:    0.5.4
 */