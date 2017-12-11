package cn.tomsnail.objsql.condition;

import java.io.Serializable;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月16日 下午4:54:30
 * @see 
 */
public abstract class ACondition implements ICondition,Serializable{

	protected String conditionKey;
	
	protected String conditionValue;
	
	protected ConditionType conditionType;
	
	protected ICondition nextCondition;
	
	public ACondition add(ICondition condition){
		if(nextCondition!=null){
			nextCondition.add(condition);
		}else{
			nextCondition = condition;
		}
		return this;
	}
	
	
	
	
	
	
}
