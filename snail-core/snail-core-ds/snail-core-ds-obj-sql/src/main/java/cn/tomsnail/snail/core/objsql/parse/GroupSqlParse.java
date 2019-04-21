package cn.tomsnail.snail.core.objsql.parse;

import cn.tomsnail.snail.core.objsql.condition.GroupCondition;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月29日 下午3:15:35
 * @see 
 */
public class GroupSqlParse extends ASqlParse{
	

	public GroupSqlParse(ASqlParse parse) {
		super(parse);
	}
	
	public GroupSqlParse(GroupCondition condition){
		super(null);
		this.ipSql = condition;
	}
	
	public GroupSqlParse(ASqlParse parse,GroupCondition condition){
		super(parse);
		if(condition==null){
			condition = new GroupCondition();
		}
		this.ipSql = condition;
	}

	@Override
	public void parse() {
		
	}

	@Override
	public String getSQL() {
		String sql = " group by "+ipSql.get();
		if(this.parse==null){
			return sql;
		}else{
			return this.parse.getSQL()+sql;
		}
	}

}
