package cn.tomsnail.snail.core.objsql.condition;

import java.io.Serializable;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月29日 下午3:04:34
 * @see 
 */
public class InSqlCondition extends ACondition implements Serializable{

	public InSqlCondition(){
		
	}
	
	public InSqlCondition(String conditionKey,String conditionValue){
		this.conditionKey = conditionKey;
		this.conditionType = ConditionType.in;
		this.conditionValue = conditionValue;
	}
	
	public InSqlCondition(String conditionValue){
		this.conditionKey = null;
		this.conditionType = ConditionType.in;
		this.conditionValue = conditionValue;
	}
	
	@Override
	public String get() {
		StringBuffer conditionStr = new StringBuffer(" ");
		if(conditionKey!=null){
			conditionStr.append(conditionKey).append(" in (");
		}
		if(this.conditionValue==null){
			conditionStr.append("");
		}else{
			conditionStr.append(this.conditionValue);
		}
		if(this.nextCondition!=null){
			if(this.nextCondition instanceof InSqlCondition){
			}else{
				conditionStr.append(" )");
			}
			conditionStr.append(" , ").append(this.nextCondition.get());
		}else{
			conditionStr.append(" )");
		}
		return conditionStr.toString();
	}
	
	public InSqlCondition add(InSqlCondition condition){
		if(nextCondition!=null){
			nextCondition.add(condition);
		}else{
			nextCondition = condition;
		}
		return this;
	}

}
