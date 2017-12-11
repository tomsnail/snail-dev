package cn.tomsnail.framwork.util;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午1:59:21
	* @see 
	*/     
    
public class MapToObj {

	public static <T> T getObj(Map<String,?> map,Class<T> clazz) throws Exception{
		if(map==null||clazz==null){
			return null;
		}
		T obj = clazz.newInstance();  
		BeanUtils.populate(obj, map);  
		return obj;
	}
	
}
