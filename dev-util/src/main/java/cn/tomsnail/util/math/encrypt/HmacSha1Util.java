package cn.tomsnail.util.math.encrypt;


import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import cn.tomsnail.util.math.BinStrUtil;

/**
 *        HMAC-SHA1数字签名算法
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年4月4日 下午4:53:13
 * @see 
 */
public class HmacSha1Util {
	
	public static final String HMAC_SHA1 = "HmacSHA1";  
	
	public static final String HMAC_SHA256 = "HmacSHA256";  
	  
    /** 
     * 生成签名数据 
     *  
     * @param data 待加密的数据 
     * @param key  加密使用的key 
     * @return 生成MD5编码的字符串  
     * @throws Exception 
     */  
    public static String getSignature(byte[] data, byte[] key)  {  
        try {
			SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);  
			Mac mac = Mac.getInstance(HMAC_SHA1);  
			mac.init(signingKey);  
			byte[] rawHmac = mac.doFinal(data);  
			return MD5Util.md5Encode(rawHmac);
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
    }  
    
    public static String getBase64Signature(byte[] data, byte[] key)  {  
        try {
			SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);  
			Mac mac = Mac.getInstance(HMAC_SHA1);  
			mac.init(signingKey);  
			byte[] rawHmac = mac.doFinal(data);  
			return Base64Util.getBase64(rawHmac);
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
    }  
    
    public static String getSoureSignature(byte[] data, byte[] key)  {  
        try {
			SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);  
			Mac mac = Mac.getInstance(HMAC_SHA1);  
			mac.init(signingKey);  
			byte[] rawHmac = mac.doFinal(data);  
			return BinStrUtil.byte2hex(rawHmac);
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
    }  
    
   
    
    public static String getSignature(byte[] data, byte[] key,String signType)  {  
        try {
			SecretKeySpec signingKey = new SecretKeySpec(key, signType);  
			Mac mac = Mac.getInstance(signType);  
			mac.init(signingKey);  
			byte[] rawHmac = mac.doFinal(data);  
			return MD5Util.md5Encode(rawHmac);
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
    }  
    
    public static String getBase64Signature(byte[] data, byte[] key,String signType)  {  
        try {
			SecretKeySpec signingKey = new SecretKeySpec(key, signType);  
			Mac mac = Mac.getInstance(signType);  
			mac.init(signingKey);  
			byte[] rawHmac = mac.doFinal(data);  
			return Base64Util.getBase64(rawHmac);
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
    }  
    
    public static String getSoureSignature(byte[] data, byte[] key,String signType)  {  
        try {
			SecretKeySpec signingKey = new SecretKeySpec(key, signType);  
			Mac mac = Mac.getInstance(signType);  
			mac.init(signingKey);  
			byte[] rawHmac = mac.doFinal(data);  
			return BinStrUtil.byte2hex(rawHmac);
		} catch (Exception e) {
			return UUID.randomUUID().toString();
		}
    }  
    
    public static void main(String[] args) {
		try {
			System.out.println(HmacSha1Util.getSoureSignature("123213".getBytes(), "123211".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
