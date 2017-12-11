package cn.tomsnail.objsql.condition;

import cn.tomsnail.objsql.IPSql;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月16日 下午4:54:34
 * @see 
 */
public interface ICondition extends IPSql{

	public ACondition add(ICondition condition);
	
	
}
