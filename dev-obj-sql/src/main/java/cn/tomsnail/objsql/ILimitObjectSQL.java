package cn.tomsnail.objsql;

import java.util.List;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午3:26:47
	* @see 
	*/     
    
public interface ILimitObjectSQL<K,T> extends ICommonIdObjectSQL<K, T> {

	public List<T> searchById(K id,int start,int limit);
	
}
