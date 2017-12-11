package cn.tomsnail.objsql.parse;

import cn.tomsnail.objsql.condition.OrderCondition;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月29日 下午3:15:28
 * @see 
 */
public class OrderSqlParse extends ASqlParse{
	
	public OrderSqlParse(ASqlParse parse) {
		super(parse);
	}
	
	public OrderSqlParse(OrderCondition condition){
		super(null);
		this.ipSql = condition;
	}
	
	public OrderSqlParse(ASqlParse parse,OrderCondition condition){
		super(parse);
		if(condition==null){
			condition = new OrderCondition();
		}
		this.ipSql = condition;
	}

	@Override
	public void parse() {
		
	}

	@Override
	public String getSQL() {
		String sql = " order by "+ipSql.get();
		if(this.parse==null){
			return sql;
		}else{
			return this.parse.getSQL()+sql;
		}
	}

	
	
}
