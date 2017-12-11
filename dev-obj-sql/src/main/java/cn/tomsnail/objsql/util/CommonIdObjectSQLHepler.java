package cn.tomsnail.objsql.util;

import java.util.List;

import cn.tomsnail.objsql.ICommonIdObjectSQL;
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
	* @date 2016年12月19日 下午12:41:13
	* @see 
	*/     
public class CommonIdObjectSQLHepler<K,T> extends BasicObjectSQLHepler<T> implements ICommonIdObjectSQL<K,T>{

	public CommonIdObjectSQLHepler(String className) {
		super(className);
	}

	@Override
	public int deleteById(K id) {
		BaseSqlCondition condition = new BaseSqlCondition(this.objectSQL.getIdname(), ConditionType.eq, id.toString());
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL);
		baseSqlParse.setSqlType(SQLType.DELETE);
		ConditionSqlParse conditionSqlParse = new ConditionSqlParse(baseSqlParse, condition);
		return dbOper.delete(conditionSqlParse.getSQL());
	}

	@Override
	public List<T> searchById(K id) {
		if(id==null){
			return null;
		}
		BaseSqlCondition condition = new BaseSqlCondition(this.objectSQL.getIdname(), ConditionType.eq, id.toString());
		LimitSqlParse limitSqlParse = new LimitSqlParse(new ConditionSqlParse(new BaseSqlParse(this.objectSQL), condition),limit,start);
		return (List<T>) dbOper.search(limitSqlParse.getSQL(), this.clazz);
	}

	@Override
	public int updateById(K id, T t) {
		BaseSqlCondition condition = new BaseSqlCondition(this.objectSQL.getIdname(), ConditionType.eq, id.toString());
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL);
		baseSqlParse.setSqlType(SQLType.UPDATE);
		ConditionSqlParse conditionSqlParse = new ConditionSqlParse(baseSqlParse, condition);
		return dbOper.update(conditionSqlParse.getSQL(t));
	}

}
