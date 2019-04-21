package cn.tomsnail.snail.core.objsql.condition;

import java.io.Serializable;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月29日 下午3:04:43
 * @see 
 */
public class OrderCondition extends ACondition implements Serializable{
	
	private int orderType;

	public OrderCondition(){
		
	}
	
	public OrderCondition(String conditonKey,int orderType){
		this.conditionKey = conditonKey;
		this.conditionType = ConditionType.order;
		this.conditionValue = "";
		this.orderType = orderType;
	}
	
	public OrderCondition(String conditonKey){
		this(conditonKey,0);
	}
	
	
	@Override
	public String get() {
		StringBuffer conditionStr = new StringBuffer(" ");
		if(this.conditionKey==null){
			conditionStr.append("");
		}else{
			if(orderType==0){
				conditionStr.append(this.conditionKey);
			}else{
				conditionStr.append(this.conditionKey).append(" desc ");
			}
		}
		if(this.nextCondition!=null){
			conditionStr.append(" , ").append(this.nextCondition.get());
		}
		return conditionStr.toString();
	}
	
	public OrderCondition add(OrderCondition condition){
		if(nextCondition!=null){
			nextCondition.add(condition);
		}else{
			nextCondition = condition;
		}
		return this;
	}

}
