/*     */ package cn.tomsnail.snail.core.ds.mybatis.dialect;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;

/**
 * The type Postgre page hepler.
 */
/*     */
/*     */ public class PostgrePageHepler
/*     */ {
    /**
     * Gets count string.
     *
     * @param querySelect the query select
     * @return the count string
     */
    /*     */   public static String getCountString(String querySelect)
/*     */   {
/*  14 */     querySelect = getLineSql(querySelect);
/*  15 */     int orderIndex = getLastOrderInsertPoint(querySelect);
/*     */ 
/*  17 */     int formIndex = getAfterFormInsertPoint(querySelect);
/*  18 */     String select = querySelect.substring(0, formIndex);
/*     */ 
/*  21 */     if ((select.toLowerCase().indexOf("select distinct") != -1) || (querySelect.toLowerCase().indexOf("group by") != -1)) {
/*  22 */       return querySelect.length() + "select count(1) count from (" + querySelect.substring(0, orderIndex) + " ) t";
/*     */     }
/*  24 */     return querySelect.length() + "select count(1) count " + querySelect.substring(formIndex, orderIndex);
/*     */   }
/*     */ 
/*     */   private static int getLastOrderInsertPoint(String querySelect)
/*     */   {
/*  34 */     int orderIndex = querySelect.toLowerCase().lastIndexOf("order by");
/*  35 */     if (orderIndex == -1) {
/*  36 */       orderIndex = querySelect.length();
/*     */     }
/*  38 */     if (!isBracketCanPartnership(querySelect.substring(orderIndex, querySelect.length()))) {
/*  39 */       throw new RuntimeException("Postgre 分页必须要有Order by 语句!");
/*     */     }
/*  41 */     return orderIndex;
/*     */   }

    /**
     * Gets limit string.
     *
     * @param querySelect the query select
     * @param offset      the offset
     * @param limit       the limit
     * @return the limit string
     */
    /*     */
/*     */   public static String getLimitString(String querySelect, int offset, int limit)
/*     */   {
/*  54 */     querySelect = getLineSql(querySelect);
/*  55 */     String sql = querySelect + " LIMIT " + limit + " OFFSET " + offset;
/*  56 */     return sql;
/*     */   }
/*     */ 
/*     */   private static String getLineSql(String sql)
/*     */   {
/*  67 */     return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
/*     */   }
/*     */ 
/*     */   private static int getAfterFormInsertPoint(String querySelect)
/*     */   {
/*  74 */     String regex = "\\s+FROM\\s+";
/*  75 */     Pattern pattern = Pattern.compile(regex, 2);
/*  76 */     Matcher matcher = pattern.matcher(querySelect);
/*  77 */     while (matcher.find()) {
/*  78 */       int fromStartIndex = matcher.start(0);
/*  79 */       String text = querySelect.substring(0, fromStartIndex);
/*  80 */       if (isBracketCanPartnership(text)) {
/*  81 */         return fromStartIndex;
/*     */       }
/*     */     }
/*  84 */     return 0;
/*     */   }
/*     */ 
/*     */   private static boolean isBracketCanPartnership(String text)
/*     */   {
/*  96 */     return (text != null) && (getIndexOfCount(text, '(') == getIndexOfCount(text, ')'));
/*     */   }
/*     */ 
/*     */   private static int getIndexOfCount(String text, char ch)
/*     */   {
/* 110 */     int count = 0;
/* 111 */     for (int i = 0; i < text.length(); ++i) {
/* 112 */       count = (text.charAt(i) == ch) ? count + 1 : count;
/*     */     }
/* 114 */     return count;
/*     */   }
/*     */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.PostgrePageHepler
 * JD-Core Version:    0.5.4
 */