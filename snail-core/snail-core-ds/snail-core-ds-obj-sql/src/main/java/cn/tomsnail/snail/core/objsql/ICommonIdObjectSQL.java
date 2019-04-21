package cn.tomsnail.snail.core.objsql;

import java.util.List;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 上午11:04:35
	* @see 
	*/     
    
public interface ICommonIdObjectSQL<K,T> extends IBasicObjectSQL<T>{

	public int deleteById(K id);
	
	public List<T> searchById(K id);
	
	public int updateById(K id,T t);
	
}
