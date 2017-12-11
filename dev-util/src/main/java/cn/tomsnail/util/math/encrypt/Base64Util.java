package cn.tomsnail.util.math.encrypt;

import java.io.UnsupportedEncodingException;  

import sun.misc.*;  
  
/**
 *        BASE64编码算法
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年4月4日 下午4:54:05
 * @see 
 */
public class Base64Util {  
	
	
	
	
    // 加密  
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
    
    // 加密
    public static String getBase64(byte[] b) {  
    	String s = null;
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    // 解密  
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  
    
    
    // 解密  
    public static byte[] getBytesFromBase64(String s) {  
        byte[] b = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return b;  
    }  
}  
