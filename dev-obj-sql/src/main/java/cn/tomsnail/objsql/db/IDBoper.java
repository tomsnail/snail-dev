package cn.tomsnail.objsql.db;

import java.util.List;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午1:36:59
	* @see 
	*/
public interface IDBoper {
	
	public String save(String sql);
	
	public int update(String sql);
	
	public int delete(String sql);
	
	public <T>  List<T>  search(String sql,Class<T>  elementType);
	
}
