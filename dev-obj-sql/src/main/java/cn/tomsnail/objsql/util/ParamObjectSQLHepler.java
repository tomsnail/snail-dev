package cn.tomsnail.objsql.util;

import java.util.List;

import cn.tomsnail.objsql.IParamObjectSQL;
import cn.tomsnail.objsql.condition.BaseSqlCondition;
import cn.tomsnail.objsql.condition.ConditionType;
import cn.tomsnail.objsql.parse.BaseSqlParse;
import cn.tomsnail.objsql.parse.ConditionSqlParse;
import cn.tomsnail.objsql.parse.LimitSqlParse;
import cn.tomsnail.objsql.parse.SQLType;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午3:35:56
	* @see 
	*/     
    
public class ParamObjectSQLHepler<K, T> extends LimitObjectSQLHepler<K, T> implements
		IParamObjectSQL<K, T> {

	public ParamObjectSQLHepler(String className) {
		super(className);
	}

	@Override
	public int updateById(K id, T t, String[] fields) {
		BaseSqlCondition condition = new BaseSqlCondition(this.objectSQL.getIdname(), ConditionType.eq, id.toString());
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL);
		baseSqlParse.setFields(fields);
		baseSqlParse.setSqlType(SQLType.UPDATE);
		ConditionSqlParse conditionSqlParse = new ConditionSqlParse(baseSqlParse, condition);
		return dbOper.update(conditionSqlParse.getSQL(t));
	}

	@Override
	public List<T> searchById(K id, String[] fields, int start, int limit) {
		if(id==null){
			return null;
		}
		if(limit<1){
			limit = 1;
		}
		BaseSqlCondition condition = new BaseSqlCondition(this.objectSQL.getIdname(), ConditionType.eq, id.toString());
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL);
		baseSqlParse.setFields(fields);
		LimitSqlParse limitSqlParse = new LimitSqlParse(new ConditionSqlParse(baseSqlParse, condition),limit,start);
		return (List<T>) dbOper.search(limitSqlParse.getSQL(), this.clazz);
	}

	@Override
	public List<T> searchById(K id, String[] fields) {
		return searchById(id,fields,this.start,this.limit);
	}

}
