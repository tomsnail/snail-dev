package cn.tomsnail.framwork.util;

import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	
	public static String toJson(Object obj){
			return JSONObject.fromObject(obj).toString();
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
		JSONObject json = JSONObject.fromObject(str);
		Iterator<?> it = json.keys();
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
		JSONArray jsonarray = JSONArray.fromObject(jsons);  
		Iterator it = jsonarray.iterator();  
        while(it.hasNext()){  
        	JSONObject p = (JSONObject)it.next();  
            System.out.println(p);  
            Object a = p.get(parameter);
            objs.add(a);
        }  
        return objs;
	}
	// java对象转换成json字符串
			public static String object2JSON(Object o) {
				ObjectMapper om = new ObjectMapper();
				Writer w = new StringWriter();
				String json = null;
				try {
					om.writeValue(w, o);
					json = w.toString();
					w.close();
				} catch (Exception e) {
				}
				return json;
			}
}
