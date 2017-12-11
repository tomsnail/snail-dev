package cn.tomsnail.util.commons;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

  
   /**
	*        Map操作常用类
	* @author yangsong
	* @version 0.0.3
	* @status 正常
	* @date 2017年9月12日 下午3:59:57
	* @see 
	*/     
public class MapUtils {

	  
	   /**
		*        得到字典排序的json字符串
		* @methodauthor yangsong
		* @methodversion 0.0.3
		* @date 2017年9月12日 下午4:00:16
		* @see 
		* @param  map       
		* @return   string      
		* @status 正常
		* @exception no
		*/
	public static String getOrderMapJson(Map<String,Object> map){
		if(map==null||map.size()==0){
			return "{}";
		}
		Collection<String> keyset= map.keySet();   
		List<String> list=new ArrayList<String>(keyset);
		Collections.sort(list);
		JSONObject jsonObject = new JSONObject(true);
		for(String s:list){
			jsonObject.put(s, map.get(s));
		}
		return jsonObject.toJSONString();
	}
	
}
