package cn.tomsnail.objsql;

import java.util.List;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午3:29:39
	* @see 
	*/     
public interface IParamObjectSQL<K,T> extends ILimitObjectSQL<K, T> {

	public int updateById(K id,T t,String[] fields);
	
	public List<T> searchById(K id, String[] fields,int start, int limit);
	
	public List<T> searchById(K id, String[] fields);
	
}
