package cn.tomsnail.snail.core.objsql.parse;

import cn.tomsnail.snail.core.objsql.IPSql;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月16日 下午6:08:27
 * @see 
 */
public abstract class ASqlParse implements ISqlParse{

	protected ASqlParse parse;
	
	protected IPSql ipSql;
	
	protected SQLType sqlType = SQLType.SELECT;
	
	public ASqlParse(ASqlParse parse){
		this.parse = parse;
		parse();
	}
	
	public ASqlParse(ASqlParse parse,SQLType sqlType){
		this(parse);
		this.sqlType = sqlType;
	}

	public ASqlParse getParse() {
		return parse;
	}

	public void setParse(ASqlParse parse) {
		this.parse = parse;
	}
	
	public String getSQL(Object obj){
		return null;
	}

	public SQLType getSqlType() {
		return sqlType;
	}

	public void setSqlType(SQLType sqlType) {
		this.sqlType = sqlType;
	}
	
	
	
}
