/*     */ package cn.tomsnail.snail.core.ds.mybatis.dialect;
/*     */ 
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;

/**
 * The type Ms page hepler.
 */
/*     */
/*     */ public class MSPageHepler
/*     */ {
    /**
     * Gets count string.
     *
     * @param querySelect the query select
     * @return the count string
     */
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
/*  56 */     querySelect = getLineSql(querySelect);
/*  57 */     int selectIndex = querySelect.toUpperCase().lastIndexOf("SELECT");
/*  58 */     if (selectIndex > -1) {
/*  59 */       querySelect = querySelect.substring(0, selectIndex) + "SELECT TOP " + (limit + offset) + querySelect.substring(selectIndex + 6);
/*     */     }
/*  61 */     String sql = "SELECT * FROM(SELECT ROW_NUMBER () OVER (ORDER BY getdate()) rownum,* FROM( " + querySelect + " ) A ) B WHERE B.rownum > " + offset + " AND B.rownum <= " + (limit + offset);
/*     */ 
/*  63 */     return sql;
/*     */   }
/*     */ 
/*     */   private static String getLineSql(String sql)
/*     */   {
/*  75 */     return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
/*     */   }
/*     */ 
/*     */   private static int getAfterFormInsertPoint(String querySelect)
/*     */   {
/*  82 */     String regex = "\\s+FROM\\s+";
/*  83 */     Pattern pattern = Pattern.compile(regex, 2);
/*  84 */     Matcher matcher = pattern.matcher(querySelect);
/*  85 */     while (matcher.find()) {
/*  86 */       int fromStartIndex = matcher.start(0);
/*  87 */       String text = querySelect.substring(0, fromStartIndex);
/*  88 */       if (isBracketCanPartnership(text)) {
/*  89 */         return fromStartIndex;
/*     */       }
/*     */     }
/*  92 */     return 0;
/*     */   }
/*     */ 
/*     */   private static boolean isBracketCanPartnership(String text)
/*     */   {
/* 104 */     return (text != null) && (getIndexOfCount(text, '(') == getIndexOfCount(text, ')'));
/*     */   }
/*     */ 
/*     */   private static int getIndexOfCount(String text, char ch)
/*     */   {
/* 118 */     int count = 0;
/* 119 */     for (int i = 0; i < text.length(); ++i) {
/* 120 */       count = (text.charAt(i) == ch) ? count + 1 : count;
/*     */     }
/* 122 */     return count;
/*     */   }
/*     */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.dialect.MSPageHepler
 * JD-Core Version:    0.5.4
 */