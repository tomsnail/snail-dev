package cn.tomsnail.objsql.condition;

import java.io.Serializable;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月16日 下午4:57:05
 * @see 
 */
public class BaseSqlCondition extends ACondition implements Serializable{

	public BaseSqlCondition(){
		
	}
	
	public BaseSqlCondition(String conditonKey,ConditionType conditionType,String conditionValue){
		this.conditionKey = conditonKey;
		this.conditionType = conditionType;
		this.conditionValue = conditionValue;
	}
	
	public String get(){
		StringBuffer conditionStr = new StringBuffer();
		if(this.conditionKey==null){
			conditionStr.append("1=1");
		}else{
			conditionStr.append(this.conditionKey).append(this.conditionType.get()).append(" '").append(this.conditionValue).append("' ");
		}
		if(this.nextCondition!=null){
			conditionStr.append(" and ").append(this.nextCondition.get());
		}
		return conditionStr.toString();
	}
	
	public static void main(String[] args) {
		BaseSqlCondition t = new BaseSqlCondition();
		t.add(new BaseSqlCondition("key1", ConditionType.gt, "1")).add(new BaseSqlCondition("key2", ConditionType.eq, "12"));
		t.add(new InSqlCondition("test","1").add(new InSqlCondition("2")));
		System.out.println(t.get());
	}
	
}
