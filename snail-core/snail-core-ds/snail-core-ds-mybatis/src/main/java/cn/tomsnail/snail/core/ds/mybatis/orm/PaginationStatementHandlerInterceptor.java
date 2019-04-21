/*     */ package cn.tomsnail.snail.core.ds.mybatis.orm;
/*     */ 
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.util.Properties;

/*     */ import org.apache.ibatis.executor.parameter.ParameterHandler;
/*     */ import org.apache.ibatis.executor.statement.StatementHandler;
/*     */ import org.apache.ibatis.mapping.BoundSql;
/*     */ import org.apache.ibatis.plugin.Interceptor;
/*     */ import org.apache.ibatis.plugin.Intercepts;
/*     */ import org.apache.ibatis.plugin.Invocation;
/*     */ import org.apache.ibatis.plugin.Plugin;
/*     */ import org.apache.ibatis.reflection.MetaObject;
/*     */ import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
/*     */ import org.apache.ibatis.reflection.factory.ObjectFactory;
/*     */ import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
/*     */ import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
/*     */ import org.apache.ibatis.session.Configuration;
/*     */ import org.apache.ibatis.session.RowBounds;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;

import cn.tomsnail.snail.core.ds.mybatis.dialect.Dialect;
import cn.tomsnail.snail.core.ds.mybatis.dialect.DialectFactory;





/*     */ 
/*     */ @Intercepts({@org.apache.ibatis.plugin.Signature(type=StatementHandler.class, method="prepare", args={Connection.class})})
/*     */ public class PaginationStatementHandlerInterceptor
/*     */   implements Interceptor
/*     */ {
/*  36 */   private static final Logger logger = LoggerFactory.getLogger(PaginationStatementHandlerInterceptor.class);
/*     */ 
/*  38 */   private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
/*  39 */   private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
/*     */ 
/*     */   public Object intercept(Invocation invocation) throws Throwable {
/*  42 */     StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
/*  43 */     ParameterHandler parameterHandler = statementHandler.getParameterHandler();
/*  44 */     BoundSql boundSql = statementHandler.getBoundSql();
/*     */ 
/*  46 */     MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
/*  47 */     RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");
/*     */ 
/*  49 */     if ((rowBounds == null) || (rowBounds == RowBounds.DEFAULT)) {
/*  50 */       return invocation.proceed();
/*     */     }
/*     */ 
/*  53 */     Configuration configuration = (Configuration)metaStatementHandler.getValue("delegate.configuration");
/*  54 */     Dialect dialect = DialectFactory.buildDialect(configuration);
/*  55 */     String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
/*     */ 
/*  57 */     Page page = (Page)rowBounds;
/*  58 */     String countSql = dialect.getCountString(originalSql);
/*  59 */     Connection connection = (Connection)invocation.getArgs()[0];
/*  60 */     int total = getTotal(parameterHandler, connection, countSql);
/*  61 */     page.setTotalCount(total);
/*     */ 
/*  64 */     metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, page.getOffset(), page.getLimit()));
/*     */ 
/*  66 */     metaStatementHandler.setValue("delegate.rowBounds.offset", Integer.valueOf(0));
/*  67 */     metaStatementHandler.setValue("delegate.rowBounds.limit", Integer.valueOf(2147483647));
/*  68 */     if (logger.isDebugEnabled()) {
/*  69 */       logger.debug("分页SQL : " + boundSql.getSql());
/*     */     }
/*  71 */     return invocation.proceed();
/*     */   }
/*     */ 
/*     */   public Object plugin(Object target) {
/*  75 */     return Plugin.wrap(target, this);
/*     */   }
/*     */ 
/*     */   public void setProperties(Properties properties)
/*     */   {
/*     */   }
/*     */ 
/*     */   private int getTotal(ParameterHandler parameterHandler, Connection connection, String countSql)
/*     */     throws Exception
/*     */   {
/*  96 */     PreparedStatement prepareStatement = connection.prepareStatement(countSql);
/*  97 */     parameterHandler.setParameters(prepareStatement);
/*  98 */     ResultSet rs = prepareStatement.executeQuery();
/*  99 */     int count = 0;
/* 100 */     if (rs.next()) {
/* 101 */       count = rs.getInt(1);
/*     */     }
/* 103 */     rs.close();
/* 104 */     prepareStatement.close();
/* 105 */     return count;
/*     */   }
/*     */ }

/* Location:           E:\02_workspace\11_maven\repository\com\gymf\gymfcore\1.0\gymfcore-1.0.jar
 * Qualified Name:     com.gymf.core.orm.mybatis.PaginationStatementHandlerInterceptor
 * JD-Core Version:    0.5.4
 */