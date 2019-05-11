package cn.tomsnail.snail.core.objsql.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.objsql.IBasicObjectSQL;
import cn.tomsnail.snail.core.objsql.ICommonIdObjectSQL;
import cn.tomsnail.snail.core.objsql.ILimitObjectSQL;
import cn.tomsnail.snail.core.objsql.IParamObjectSQL;
import cn.tomsnail.snail.core.objsql.ISearchObjectSQL;
import cn.tomsnail.snail.core.objsql.db.IDBoper;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 上午11:45:20
	* @see 
	*/     

@Component
public class ObjectSQLFactory {
	
	@Resource
	private IDBoper dBoper;

	@SuppressWarnings("rawtypes")
	private static final Map<String,IBasicObjectSQL> OBJECT_SQL_MAP = new HashMap<String,IBasicObjectSQL>();
	
	
	public ObjectSQLFactory(){
	}
	@SuppressWarnings("unchecked")
	public <T>  IBasicObjectSQL<T> getBasicObjectSQL(Class<T> t){
		if(OBJECT_SQL_MAP.containsKey(t.getName()+"_BasicObjectSQL")){
		}else{
			BasicObjectSQLHepler<T> hepler = new BasicObjectSQLHepler<T>(t.getName());
			hepler.setDbOper(dBoper);
			OBJECT_SQL_MAP.put(t.getName()+"_BasicObjectSQL", hepler);
		}
		return OBJECT_SQL_MAP.get(t.getName()+"_BasicObjectSQL");
	}
	@SuppressWarnings("unchecked")
	public <K, T>  ICommonIdObjectSQL<K, T> getCommonIdObjectSQL(Class<T> t){
		if(OBJECT_SQL_MAP.containsKey(t.getName()+"_CommonIdObjectSQL")){
		}else{
			CommonIdObjectSQLHepler<K,T> hepler = new CommonIdObjectSQLHepler<K,T>(t.getName());
			hepler.setDbOper(dBoper);
			OBJECT_SQL_MAP.put(t.getName()+"_CommonIdObjectSQL", hepler);
		}
		return (ICommonIdObjectSQL<K, T>) OBJECT_SQL_MAP.get(t.getName()+"_CommonIdObjectSQL");
	}
	@SuppressWarnings("unchecked")
	public  <K,T>  ISearchObjectSQL<K,T> getSearchObjectSQL(Class<T> t){
		if(OBJECT_SQL_MAP.containsKey(t.getName()+"_SearchObjectSQL")){
		}else{
			SearchObjectSQLHepler<K,T> hepler = new SearchObjectSQLHepler<K,T>(t.getName());
			hepler.setDbOper(dBoper);
			OBJECT_SQL_MAP.put(t.getName()+"_SearchObjectSQL", hepler);
		}
		return (ISearchObjectSQL<K, T>) OBJECT_SQL_MAP.get(t.getName()+"_SearchObjectSQL");
	}
	
	@SuppressWarnings("unchecked")
	public  <K, T> ILimitObjectSQL<K, T> getLimitObjectSQL(Class<T> t){
		if(OBJECT_SQL_MAP.containsKey(t.getName()+"_LimitObjectSQL")){
		}else{
			LimitObjectSQLHepler<K,T> hepler = new LimitObjectSQLHepler<K,T>(t.getName());
			hepler.setDbOper(dBoper);
			OBJECT_SQL_MAP.put(t.getName()+"_LimitObjectSQL", hepler);
		}
		return (ILimitObjectSQL<K, T>) OBJECT_SQL_MAP.get(t.getName()+"_LimitObjectSQL");
	}
	@SuppressWarnings("unchecked")
	public  <K, T>  IParamObjectSQL<K, T> getParamObjectSQL(Class<T> t){
		if(OBJECT_SQL_MAP.containsKey(t.getName()+"_ParamObjectSQL")){
		}else{
			ParamObjectSQLHepler<K,T> hepler = new ParamObjectSQLHepler<K,T>(t.getName());
			hepler.setDbOper(dBoper);
			OBJECT_SQL_MAP.put(t.getName()+"_ParamObjectSQL", hepler);
		}
		return (IParamObjectSQL<K, T>) OBJECT_SQL_MAP.get(t.getName()+"_ParamObjectSQL");
	}
	
}
