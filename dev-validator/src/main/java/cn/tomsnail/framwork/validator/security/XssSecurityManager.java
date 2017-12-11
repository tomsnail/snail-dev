package cn.tomsnail.framwork.validator.security;

import java.util.regex.Pattern;

public class XssSecurityManager {
	// 危险的javascript:关键字j av a script  
    private final static Pattern[] DANGEROUS_TOKENS = new Pattern[] { Pattern  
            .compile("^j\\s*a\\s*v\\s*a\\s*s\\s*c\\s*r\\s*i\\s*p\\s*t\\s*:",  
                    Pattern.CASE_INSENSITIVE) };  
  
    // javascript:替换字符串（全角中文字符）  
    private final static String[] DANGEROUS_TOKEN_REPLACEMENTS = new String[] { "js:" };  
  
    // 非法的字符集  
    private static final String[] INVALID_CHARS = new String[] { "<", ">",   
            "\"", "\\" };  
  
    // 统一替换可能造成XSS漏洞的字符为全角中文字符  
    private static final String[] VALID_CHARS = new String[] { "&lt;", "&gt;", "&quot;",  
            "&quot;&quot;"};  
  
    // 开启xss过滤功能开关  
    //public static boolean enable=false;  
    
    
    public static String replaceSpecialChars(String str) {  
        for (int j = 0; j < INVALID_CHARS.length; ++j) {  
            if (str.indexOf(INVALID_CHARS[j]) >= 0) {  
                str = str.replaceAll(INVALID_CHARS[j], VALID_CHARS[j]);  
            }  
        }  
        str=str.trim();  
        for (int i = 0; i < DANGEROUS_TOKENS.length; ++i) {  
            str = DANGEROUS_TOKENS[i].matcher(str).replaceAll(  
                    DANGEROUS_TOKEN_REPLACEMENTS[i]);  
        }  
  
        return str;  
    }  
  
    /** 
     * 判断是否为空串，建议放到某个工具类中 
     *  
     * @param value 
     * @return 
     */  
    private static boolean isNullStr(String value) {  
        return value == null || value.trim().length()==0;  
    } 
}
