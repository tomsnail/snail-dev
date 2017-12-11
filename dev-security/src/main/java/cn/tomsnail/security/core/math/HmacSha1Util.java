package cn.tomsnail.security.core.math;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *        HMAC-SHA1数字签名算法
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年4月4日 下午4:53:13
 * @see 
 */
public class HmacSha1Util {
	
	private static final String HMAC_SHA1 = "HmacSHA1";  
	  
    /** 
     * 生成签名数据 
     *  
     * @param data 待加密的数据 
     * @param key  加密使用的key 
     * @return 生成MD5编码的字符串  
     * @throws Exception 
     */  
    public static String getSignature(byte[] data, byte[] key) throws Exception {  
        SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);  
        Mac mac = Mac.getInstance(HMAC_SHA1);  
        mac.init(signingKey);  
        byte[] rawHmac = mac.doFinal(data);  
        return MD5Util.md5Encode(rawHmac);
    }  
    
    public static void main(String[] args) {
		try {
			System.out.println(HmacSha1Util.getSignature("dfsdgt4t43".getBytes(), "123456".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
