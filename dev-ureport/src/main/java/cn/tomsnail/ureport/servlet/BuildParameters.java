package cn.tomsnail.ureport.servlet;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bstek.ureport.exception.ReportComputeException;

public class BuildParameters {

	public static Map<String, Object> buildParameters(HttpServletRequest req) {
		Map<String,Object> parameters=new HashMap<String,Object>();
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Enumeration<?> enumeration=req.getParameterNames();
		while(enumeration.hasMoreElements()){
			Object obj=enumeration.nextElement();
			if(obj==null){
				continue;
			}
			String name=obj.toString();
			String value=req.getParameter(name);
			if(name==null || value==null || name.startsWith("_")){
				continue;
			}
			parameters.put(name, _decode(value));
		}
		return parameters;
	}
	
	private static  String _decode(String value){
		if(value==null){
			return value;
		}
		try{
			String t = new String(value.getBytes("ISO8859-1"),"utf-8");
			value=URLDecoder.decode(t, "utf-8");
			value=URLDecoder.decode(t, "utf-8");
			return value;
		}catch(Exception ex){
			throw new ReportComputeException(ex);
		}
	}
	
}
