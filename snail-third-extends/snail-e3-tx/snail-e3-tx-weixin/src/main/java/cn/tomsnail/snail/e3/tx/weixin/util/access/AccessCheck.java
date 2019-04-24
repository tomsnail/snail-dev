package cn.tomsnail.snail.e3.tx.weixin.util.access;


import cn.tomsnail.snail.e3.tx.weixin.util.Constants;
import cn.tomsnail.snail.e3.tx.weixin.util.common.SHA1;



public class AccessCheck {
	
	private static final String ERROR = "error";

	public static String check(String signature,String timestramp,String nonce,String echostr){
		
		if (signature == null
	            || signature.length() > 128
	            || timestramp == null
	            || timestramp.length() > 128
	            || nonce == null
	            || nonce.length() > 128) {
	            return ERROR;
	        }
	        try {
	            if( SHA1.checkSignature(Constants.TOKEN, signature,timestramp, nonce)){
	                return echostr;
	            }
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
		return ERROR;
	}
	
	
	
}
