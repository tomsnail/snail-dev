/*     */ package cn.tomsnail.mybatis.orm;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.ibatis.session.RowBounds;
/*     */ 
/*     */ public class Page<T> extends RowBounds
/*     */ {
/*  20 */   protected int pageNo = 1;
/*     */ 
/*  24 */   protected int pageSize = 15;
/*     */   protected int offset;
/*     */   protected int limit;
/*  40 */   protected List<T> result = new ArrayList();
/*     */   protected int totalCount;
/*     */   protected int totalPages;
/*     */ 
/*     */   private void calcOffset()
/*     */   {
/*  57 */     this.offset = ((this.pageNo - 1) * this.pageSize);
/*     */   }
/*     */ 
/*     */   private void calcLimit()
/*     */   {
/*  64 */     this.limit = this.pageSize;
/*     */   }
/*     */ 
/*     */   public Page()
/*     */   {
/*  69 */     calcOffset();
/*  70 */     calcLimit();
/*     */   }
/*     */ 
/*     */   public Page(int pageNo, int pageSize) {
/*  74 */     this.pageNo = pageNo;
/*  75 */     this.pageSize = pageSize;
/*  76 */     calcOffset();
/*  77 */     calcLimit();
/*     */   }
/*     */ 
/*     */   public int getPageNo()
/*     */   {
/*  85 */     return this.pageNo;
/*     */   }
/*     */ 
/*     */   public int getPageSize()
/*     */   {
/*  92 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public int getFirst()
/*     */   {
/*  99 */     return (this.pageNo - 1) * this.pageSize + 1;
/*     */   }
/*     */ 
/*     */   public int getOffset()
/*     */   {
/* 106 */     return this.offset;
/*     */   }
/*     */ 
/*     */   public int getLimit() {
/* 110 */     return this.limit;
/*     */   }
/*     */ 
/*     */   public List<T> getResult()
/*     */   {
/* 118 */     return this.result;
/*     */   }
/*     */ 
/*     */   public void setResult(List<T> result)
/*     */   {
/* 125 */     this.result = result;
/*     */   }
/*     */ 
/*     */   public int getTotalCount()
/*     */   {
/* 132 */     return this.totalCount;
/*     */   }
/*     */ 
/*     */   public void setTotalCount(int totalCount)
/*     */   {
/* 139 */     this.totalCount = totalCount;
/* 140 */     this.totalPages = getTotalPages();
/*     */   }
/*     */ 
/*     */   public int getTotalPages()
/*     */   {
/* 147 */     if (this.totalCount < 0) {
/* 148 */       return -1;
/*     */     }
/* 150 */     int pages = this.totalCount / this.pageSize;
/* 151 */     return (this.totalCount % this.pageSize > 0) ? ++pages : pages;
/*     */   }
/*     */ 
/*     */   public void setTotalPages(int totalPages) {
/* 155 */     this.totalPages = totalPages;
/*     */   }
/*     */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.mybatis.Page
 * JD-Core Version:    0.5.4
 */