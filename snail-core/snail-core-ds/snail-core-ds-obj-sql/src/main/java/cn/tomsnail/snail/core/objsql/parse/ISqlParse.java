package cn.tomsnail.snail.core.objsql.parse;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月16日 下午6:08:17
 * @see 
 */
public interface ISqlParse {

	public void parse();
	
	public String getSQL();
	
	public String getSQL(Object obj);
	
}
