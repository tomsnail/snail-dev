package cn.tomsnail.snail.core.objsql;

import java.util.List;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 上午11:03:19
	* @see 
	*/     
    
public interface IBasicObjectSQL<T> {

	public String save(T t);
	
	public int update(T t);
		
	public List<T> search();
		
}
