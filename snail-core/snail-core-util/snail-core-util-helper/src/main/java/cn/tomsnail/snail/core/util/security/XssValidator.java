package cn.tomsnail.snail.core.util.security;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssValidator implements IValidator{

	private static Map<String,String> xssMap = new LinkedHashMap<String,String>();
	
	
	static {
		 // 含有脚本： script
        xssMap.put("[s|S][c|C][r|R][i|C][p|P][t|T]", "");
        // 含有脚本 javascript
        xssMap.put("[\\\"\\\'][\\s]*[j|J][a|A][v|V][a|A][s|S][c|C][r|R][i|I][p|P][t|T]:(.*)[\\\"\\\']", "\"\"");
        // 含有函数： eval
        xssMap.put("[e|E][v|V][a|A][l|L]\\((.*)\\)", "");
        // 含有符号 <
        xssMap.put("<", "&lt;");
        // 含有符号 >
        xssMap.put(">", "&gt;");
        // 含有符号 (
        xssMap.put("\\(", "(");
        // 含有符号 )
        xssMap.put("\\)", ")");
        // 含有符号 '
        xssMap.put("'", "'");
        // 含有符号 "
        xssMap.put("\"", "");
	}
	
	

	@Override
	public boolean validate(Object obj) {
		if(obj==null){
			return false;
		}
		if(obj instanceof String){
			 Set<String> keySet = xssMap.keySet();
		        for(String key : keySet){
		        	Pattern p = Pattern.compile(key);
		            Matcher m = p.matcher((String)obj);
		            if(m.find()){
		            	return false;
		            }
		        }
		      return true;
		}
		return false;
	}

	@Override
	public String clean(String str) {
		if(str==null){
			return null;
		}
		return cleanXSS(str);
	}
	
	
	/**
	 *        清除xss脚本
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年4月5日 下午1:03:46
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	private String cleanXSS(String value) {
        Set<String> keySet = xssMap.keySet();
        for(String key : keySet){
            String v = xssMap.get(key);
            value = value.replaceAll(key,v);
        }
        return value;
    }
	
	public static void main(String[] args) {
		System.out.println(new XssValidator().validate("javascript"));
	}
	
	
}
