package cn.tomsnail.snail.core.objsql.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.tomsnail.snail.core.objsql.ISearchObjectSQL;
import cn.tomsnail.snail.core.objsql.condition.BaseSqlCondition;
import cn.tomsnail.snail.core.objsql.condition.ConditionType;
import cn.tomsnail.snail.core.objsql.condition.OrderCondition;
import cn.tomsnail.snail.core.objsql.param.IParam;
import cn.tomsnail.snail.core.objsql.param.ParamType;
import cn.tomsnail.snail.core.objsql.param.SummParam;
import cn.tomsnail.snail.core.objsql.parse.ASqlParse;
import cn.tomsnail.snail.core.objsql.parse.BaseSqlParse;
import cn.tomsnail.snail.core.objsql.parse.ConditionSqlParse;
import cn.tomsnail.snail.core.objsql.parse.LimitSqlParse;
import cn.tomsnail.snail.core.objsql.parse.OrderSqlParse;
import cn.tomsnail.snail.core.objsql.parse.SQLType;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月20日 上午9:42:50
	* @see 
	*/     
    
public class SearchObjectSQLHepler<K,T> extends ParamObjectSQLHepler<K, T> implements ISearchObjectSQL<K,T>{

	public SearchObjectSQLHepler(String className) {
		super(className);
	}

	public List<T> searchByField(String fieldName,String value, String[] fields,int start, int limit){
		return this._search(fieldName, value, fields, start, limit, fieldName, 1);
	}
	
	public List<T> searchByField(String fieldName,String value,  String[] fields){
		return this._search(fieldName, value, fields, start, limit, fieldName, 1);
	}
	
	public List<T> searchByField(String fieldName,String value, int start, int limit){
		return this._search(fieldName, value, null, start, limit, fieldName, 1);
	}
	
	public List<T> searchByField(String fieldName,String value){
		return this._search(fieldName, value, null, start, limit, fieldName, 1);
	}
	
	public List<T> searchByFieldOrderDesc(String fieldName,String value, String[] fields,int start, int limit,String orderName){
		return this._search(fieldName, value, fields, start, limit, orderName, 1);
	}
	
	public List<T> searchByFieldOrder(String fieldName,String value, String[] fields,int start, int limit,String orderName){
		return this._search(fieldName, value, fields, start, limit, orderName, 0);
	}
	
	private List<T> _search(String fieldName,String value, String[] fields,int start, int limit,String orderName,int orderType){
		
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL);
		baseSqlParse.setFields(fields);
		
		BaseSqlCondition condition = new BaseSqlCondition(fieldName, ConditionType.eq, value);
		ConditionSqlParse conditionSqlParse = new ConditionSqlParse(baseSqlParse, condition);
		
		OrderCondition orderCondition = new OrderCondition(orderName, orderType);
		OrderSqlParse osp = new OrderSqlParse(conditionSqlParse, orderCondition);
		
		LimitSqlParse limitSqlParse = new LimitSqlParse(osp,limit,start);
		return (List<T>) dbOper.search(limitSqlParse.getSQL(), this.clazz);
	}

	@Override
	public int countByField(String fieldName, String value) {
		
		IParam param = new SummParam(ParamType.count, 0,"a");
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL,param);
		
		BaseSqlCondition condition = new BaseSqlCondition(fieldName, ConditionType.eq, value);
		ConditionSqlParse conditionSqlParse = new ConditionSqlParse(baseSqlParse, condition);
		
		return dbOper.search(conditionSqlParse.getSQL(), _Count.class).get(0).getA();
	}

	@Override
	public int countAll() {
		IParam param = new SummParam(ParamType.count, 0,"a");
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL,param);
		
		
		return dbOper.search(baseSqlParse.getSQL(), _Count.class).get(0).getA();
	}

	@Override
	public List<T> searchLikeField(String fieldName, String value) {
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL);
		
		BaseSqlCondition condition = new BaseSqlCondition(fieldName, ConditionType.like, value);
		ConditionSqlParse conditionSqlParse = new ConditionSqlParse(baseSqlParse, condition);
		LimitSqlParse limitSqlParse = new LimitSqlParse(conditionSqlParse,limit,start);
		return (List<T>) dbOper.search(limitSqlParse.getSQL(), this.clazz);
	}

	@Override
	public int updateByIdAndField(K id, T t, String fieldName, String value) {
		BaseSqlCondition condition = new BaseSqlCondition(this.objectSQL.getIdname(), ConditionType.eq, id.toString());
		condition.add(new BaseSqlCondition(fieldName, ConditionType.eq, value));
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL);
		baseSqlParse.setSqlType(SQLType.UPDATE);
		ConditionSqlParse conditionSqlParse = new ConditionSqlParse(baseSqlParse, condition);
		return dbOper.update(conditionSqlParse.getSQL(t));
	}

	@Override
	public List<T> search(Map<String, String> paramMap,String[] orders, int start, int limit) {
		BaseSqlCondition baseSqlCondition = new BaseSqlCondition();
		if(paramMap!=null){
			Iterator<Map.Entry<String,String>> it = paramMap.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String,String> entry = it.next();
				baseSqlCondition.add(new BaseSqlCondition(entry.getKey(), ConditionType.eq, entry.getValue()));
			}
		}
		BaseSqlParse baseSqlParse = new BaseSqlParse(this.objectSQL);
		ConditionSqlParse conditionSqlParse = new ConditionSqlParse(baseSqlParse, baseSqlCondition);
		
		ASqlParse osp = null;
		
		if(orders!=null){
			OrderCondition orderCondition = null;
			for(String order:orders){
				if(orderCondition==null){
					orderCondition = new OrderCondition(order);
				}else{
					orderCondition.add(new OrderCondition(order));
				}
			}
			osp = new OrderSqlParse(conditionSqlParse, orderCondition);
		}else{
			osp = conditionSqlParse;
		}
		
		LimitSqlParse limitSqlParse = new LimitSqlParse(osp,limit,start);
		return (List<T>) dbOper.search(limitSqlParse.getSQL(), this.clazz);
	}

}
