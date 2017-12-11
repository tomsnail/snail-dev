package cn.tomsnail.framwork.validator.security;

public class EscapeSecurityManager {

	  private static final String[] INVALID_CHARS = new String[] { "@", "%",   
          "_"};  
	  
	  private static final String[] VALID_CHARS = new String[] { "\\\\@", "\\\\%",   
      "\\\\_"};  
	  
	  
	  public static String replaceSpecialChars(String str) {  
	        for (int j = 0; j < INVALID_CHARS.length; ++j) {  
	            if (str.indexOf(INVALID_CHARS[j]) >= 0) {  
	                str = str.replaceAll(INVALID_CHARS[j], VALID_CHARS[j]);  
	            }  
	        }
	        return str;  
	    }  
	public static void main(String[] args) {
		System.out.println(replaceSpecialChars("%"));
	}
}
