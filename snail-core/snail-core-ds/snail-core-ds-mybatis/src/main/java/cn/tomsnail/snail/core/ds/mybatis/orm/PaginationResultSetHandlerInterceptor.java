/*    */ package cn.tomsnail.snail.core.ds.mybatis.orm;
/*    */ 
/*    */ import java.util.Properties;
/*    */ import org.apache.ibatis.executor.resultset.FastResultSetHandler;
/*    */ import org.apache.ibatis.plugin.Interceptor;
/*    */ import org.apache.ibatis.plugin.Intercepts;
/*    */ import org.apache.ibatis.plugin.Invocation;
/*    */ import org.apache.ibatis.plugin.Plugin;
/*    */ import org.apache.ibatis.reflection.MetaObject;
/*    */ import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
/*    */ import org.apache.ibatis.reflection.factory.ObjectFactory;
/*    */ import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
/*    */ import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
/*    */ import org.apache.ibatis.session.RowBounds;
/*    */ 
/*    */ @Intercepts({@org.apache.ibatis.plugin.Signature(type=org.apache.ibatis.executor.resultset.ResultSetHandler.class, method="handleResultSets", args={java.sql.Statement.class})})
/*    */ public class PaginationResultSetHandlerInterceptor
/*    */   implements Interceptor
/*    */ {
/* 26 */   private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
/* 27 */   private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
/*    */ 
/*    */   public Object intercept(Invocation invocation) throws Throwable {
/* 30 */     FastResultSetHandler resultSetHandler = (FastResultSetHandler)invocation.getTarget();
/* 31 */     MetaObject metaStatementHandler = MetaObject.forObject(resultSetHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
/* 32 */     RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("rowBounds");
/*    */ 
/* 34 */     Object result = invocation.proceed();
/*    */ 
/* 36 */     if (rowBounds instanceof Page) {
/* 37 */       metaStatementHandler.setValue("rowBounds.result", result);
/*    */     }
/* 39 */     return result;
/*    */   }
/*    */ 
/*    */   public Object plugin(Object target) {
/* 43 */     return Plugin.wrap(target, this);
/*    */   }
/*    */ 
/*    */   public void setProperties(Properties properties)
/*    */   {
/*    */   }
/*    */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.mybatis.PaginationResultSetHandlerInterceptor
 * JD-Core Version:    0.5.4
 */