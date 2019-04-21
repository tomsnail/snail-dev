package cn.tomsnail.snail.core.objsql.util;

import java.util.List;

import cn.tomsnail.snail.core.objsql.parse.BaseSqlParse;
import cn.tomsnail.snail.core.objsql.parse.LimitSqlParse;
import cn.tomsnail.snail.core.objsql.parse.SQLType;



  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午12:41:08
	* @see 
	*/     
    
public class BasicObjectSQLHepler<T> extends AObjectSQL<T>{

	public BasicObjectSQLHepler(String className) {
		super(className);
	}

	@Override
	public String save(T t) {
		BaseSqlParse baseSqlParse = new BaseSqlParse(SQLType.INSERT);
		baseSqlParse.setObj(this.objectSQL);
		String sql = baseSqlParse.getSQL(t);
		return dbOper.save(sql);
	}

	@Override
	public int update(T t) {
		return 0;
	}

	@Override
	public List<T> search() {
		LimitSqlParse limitSqlParse = new LimitSqlParse(new BaseSqlParse(this.objectSQL),limit,start);
		System.out.println(clazz.getName());
		return (List<T>) dbOper.search(limitSqlParse.getSQL(), clazz);
	}

}
