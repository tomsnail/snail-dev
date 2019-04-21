package cn.tomsnail.snail.core.objsql;

import java.util.List;
import java.util.Map;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午3:29:39
	* @see 
	*/     
public interface ISearchObjectSQL<K,T> extends IParamObjectSQL<K, T> {

	
	public List<T> searchByField(String fieldName,String value, String[] fields,int start, int limit);
	
	public List<T> searchByField(String fieldName,String value,  String[] fields);
	
	public List<T> searchByField(String fieldName,String value, int start, int limit);
	
	public List<T> searchByField(String fieldName,String value);
	
	public int countByField(String fieldName,String value);
	
	public int countAll();
	
	public List<T> searchByFieldOrderDesc(String fieldName,String value, String[] fields,int start, int limit,String orderName);
	
	public List<T> searchByFieldOrder(String fieldName,String value, String[] fields,int start, int limit,String orderName);
	
	public List<T> searchLikeField(String fieldName,String value);
	
	public int updateByIdAndField(K id,T t,String fieldName,String value);
	
	public List<T> search(Map<String,String> paramMap,String[] orders,int start,int limit);

	
}
