package cn.tomsnail.objsql.condition;

import java.io.Serializable;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月29日 下午3:04:20
 * @see 
 */
public class GroupCondition extends ACondition implements Serializable{

	public GroupCondition(){
		
	}
	
	public GroupCondition(String conditonKey){
		this.conditionKey = conditonKey;
		this.conditionType = ConditionType.group;
		this.conditionValue = "";
	}
	
	@Override
	public String get() {
		StringBuffer conditionStr = new StringBuffer(" ");
		if(this.conditionKey==null){
			conditionStr.append("");
		}else{
			conditionStr.append(this.conditionKey);
		}
		if(this.nextCondition!=null){
			conditionStr.append(" , ").append(this.nextCondition.get());
		}
		return conditionStr.toString();
	}
	
	public GroupCondition add(GroupCondition condition){
		if(nextCondition!=null){
			nextCondition.add(condition);
		}else{
			nextCondition = condition;
		}
		return this;
	}

}
