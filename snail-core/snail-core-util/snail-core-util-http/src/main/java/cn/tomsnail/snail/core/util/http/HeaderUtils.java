package cn.tomsnail.snail.core.util.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HeaderUtils {

	/**
	 * 获得指定header的值
	 * @param name 名称
	 * @return 值
	 */
	public static String getHeader(HttpServletRequest request, String name) {
		return request.getHeader(name);
	}
	
	/**
	 * 设置header的值
	 * @param name 名称
	 * @param value 值
	 * @return 值
	 */
	public static void setHeader(HttpServletResponse response, String name,String value) {
		if(response.containsHeader(name)){
			response.setHeader(name, value);
		}else{
			response.addHeader(name, value);
		}
	}
	
	/**
	 * 清除header的值
	 * @param name 名称
	 * @param value 值
	 * @return 值
	 */
	public static void clearHeader(HttpServletResponse response, String name) {
		if(response.containsHeader(name)){
			response.setHeader(name,"");
		}
	}
	
}
