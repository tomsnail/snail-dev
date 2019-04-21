package cn.tomsnail.snail.core.objsql.parse;

import cn.tomsnail.snail.core.objsql.condition.BaseSqlCondition;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月16日 下午6:08:11
 * @see 
 */
public class ConditionSqlParse extends ASqlParse{
	
	public ConditionSqlParse(ASqlParse parse) {
		super(parse);
	}
	
	public ConditionSqlParse(ConditionSqlParse parse,SQLType sqlType){
		this(parse);
		this.sqlType = sqlType;
	}
	
	public ConditionSqlParse(BaseSqlCondition baseSqlCondition){
		super(null);
		this.ipSql = baseSqlCondition;
	}
	
	public ConditionSqlParse(ASqlParse parse,BaseSqlCondition baseSqlCondition){
		super(parse);
		if(baseSqlCondition==null){
			baseSqlCondition = new BaseSqlCondition();
		}
		this.ipSql = baseSqlCondition;
	}

	@Override
	public void parse() {
	}

	@Override
	public String getSQL() {
		String sql = " where "+ipSql.get();
		if(this.parse==null){
			return sql;
		}else{
			return this.parse.getSQL()+sql;
		}
	}
	
	@Override
	public String getSQL(Object t) {
		String sql = " where "+ipSql.get();
		if(this.parse==null){
			return sql;
		}else{
			return this.parse.getSQL(t)+sql;
		}
	}
	

}
