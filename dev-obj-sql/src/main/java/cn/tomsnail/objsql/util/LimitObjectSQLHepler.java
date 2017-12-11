package cn.tomsnail.objsql.util;

import java.util.List;

import cn.tomsnail.objsql.ILimitObjectSQL;
import cn.tomsnail.objsql.condition.BaseSqlCondition;
import cn.tomsnail.objsql.condition.ConditionType;
import cn.tomsnail.objsql.parse.BaseSqlParse;
import cn.tomsnail.objsql.parse.ConditionSqlParse;
import cn.tomsnail.objsql.parse.LimitSqlParse;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午3:25:49
	* @see 
	*/     
    
public class LimitObjectSQLHepler<K,T> extends CommonIdObjectSQLHepler<K, T> implements ILimitObjectSQL<K, T>{

	public LimitObjectSQLHepler(String className) {
		super(className);
	}

	@Override
	public List<T> searchById(K id, int start, int limit) {
		if(id==null){
			return null;
		}
		if(limit<1){
			limit = 1;
		}
		BaseSqlCondition condition = new BaseSqlCondition(this.objectSQL.getIdname(), ConditionType.eq, id.toString());
		LimitSqlParse limitSqlParse = new LimitSqlParse(new ConditionSqlParse(new BaseSqlParse(this.objectSQL), condition),limit,start);
		return (List<T>) dbOper.search(limitSqlParse.getSQL(), this.clazz);
	}
	
	

}
