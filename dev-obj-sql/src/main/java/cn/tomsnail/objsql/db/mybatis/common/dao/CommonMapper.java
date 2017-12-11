package cn.tomsnail.objsql.db.mybatis.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;




  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午1:50:28
	* @see 
	*/     
 @Repository
public interface CommonMapper {
		
	public int saveData(Map map);
	
	public int updateData(Map map);
	
	public int deleteData(Map map);
	
	public List<Map<String,Object>> searchData(Map map);
}
