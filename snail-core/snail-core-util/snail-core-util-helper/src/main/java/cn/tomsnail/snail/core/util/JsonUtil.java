package cn.tomsnail.snail.core.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;


public class JsonUtil {
	
	
	public static String toJson(Object obj){
		if(Objects.isNull(obj)) {
			obj = new Object();
		}
		return JSON.toJSONString(obj);
	}
	
	public static String toNotNullObjectJson(Object obj){
		return JSON.toJSONString(obj,SerializerFeature.WriteMapNullValue);
	}
	
	public static String toNotNullMapJson(Object obj){
		return JSON.toJSONString(obj,filter);
	}
	
	
	 private static ValueFilter filter = new ValueFilter() {
	        @Override
	        public Object process(Object obj, String s, Object v) {
	            if (v == null)
	                return "";
	            return v;
	        }
	    };
	
	public static <T> T getObject(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}
	
	/***
	* @Description: json转换为map
	* @author  RTX
	* @param   @param str
	* @param   @return
	* @return  Map<String,String>
	* @data  2015-8-21 上午9:26:28
	 */
	public static Map<String, Object> getMap(String str){
		Map<String,Object> data = new HashMap<String,Object>();
		JSONObject json = JSON.parseObject(str);
		Iterator<?> it = json.entrySet().iterator();
		while(it.hasNext()){
			String key = String.valueOf(it.next());
			String value = json.get(key).toString();
			data.put(key, value);
		}
		return data;
	}
	/**
	* @Description:
	* @author  RTX
	* @param <T>
	* @param   @param str
	* @param   @return
	* @return  List<?>
	* @data  2015-8-20 下午5:19:47
	 */
	public static  List<Object> getList(String jsons,String parameter){
		List<Object> objs=new ArrayList<Object>();
		JSONObject json = JSON.parseObject(jsons);
		Iterator<?> it = json.entrySet().iterator();
        while(it.hasNext()){  
        	JSONObject p = (JSONObject)it.next();  
            Object a = p.get(parameter);
            objs.add(a);
        }  
        return objs;
	}
	// java对象转换成json字符串
			
}
