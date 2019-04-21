package cn.tomsnail.snail.core.objsql.parse;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月16日 下午6:08:21
 * @see 
 */
public class LimitSqlParse extends ASqlParse{
	
	private int limit = 1000;
	
	private int start = 0;

	public LimitSqlParse(ASqlParse parse) {
		super(parse);
	}
	public LimitSqlParse(ASqlParse parse,int limit,int start){
		super(parse);
		this.limit = limit;
		this.start = start;
	}

	@Override
	public void parse() {
	}

	@Override
	public String getSQL() {
		String sql = " limit "+start+" , "+limit;
		if(this.parse==null){
			return sql;
		}else{
			return this.parse.getSQL()+sql;
		}
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

}
