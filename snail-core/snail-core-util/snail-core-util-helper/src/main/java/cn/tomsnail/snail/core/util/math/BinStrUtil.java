package cn.tomsnail.snail.core.util.math;

public class BinStrUtil {

	 public static String byte2hex(byte[] b)   
	    {  
	       StringBuffer sb = new StringBuffer();  
	       String tmp = "";  
	       for (int i = 0; i < b.length; i++) {  
	        tmp = Integer.toHexString(b[i] & 0XFF);  
	        if (tmp.length() == 1){  
	            sb.append("0" + tmp);  
	        }else{  
	            sb.append(tmp);  
	        }  
	          
	       }  
	       return sb.toString();  
	    }  
	 
	 public static byte[] hex2byte(String str) {   
		  if (str == null){  
		   return null;  
		  }  
		    
		  str = str.trim();  
		  int len = str.length();  
		    
		  if (len == 0 || len % 2 == 1){  
		   return null;  
		  }  
		    
		  byte[] b = new byte[len / 2];  
		  try {  
		       for (int i = 0; i < str.length(); i += 2) {  
		            b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();  
		       }  
		       return b;  
		  } catch (Exception e) {  
		   return null;  
		  }  
		}  
}
