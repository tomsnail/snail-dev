package cn.tomsnail.weixin.util.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import cn.tomsnail.util.math.encrypt.MD5Util;

public class SHA1 {

	/**
	 * 用SHA1算法生成安全签名
	 *
	 * @param params
	 *            [token, timestamp, nonce, encrypt]
	 * @return 安全签名
	 */
	 public static boolean checkSignature(String token,String signature, String timestamp, String nonce) {  
	        String[] arr = new String[] { token, timestamp, nonce };  
	        // 将token、timestamp、nonce三个参数进行字典序排序  
	        Arrays.sort(arr);  
	        StringBuilder content = new StringBuilder();  
	        for (int i = 0; i < arr.length; i++) {  
	            content.append(arr[i]);  
	        }  
	        MessageDigest md = null;  
	        String tmpStr = null;  
	  
	        try {  
	            md = MessageDigest.getInstance("SHA-1");  
	            // 将三个参数字符串拼接成一个字符串进行sha1加密  
	            byte[] digest = md.digest(content.toString().getBytes());  
	            tmpStr = byteToStr(digest);  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        }  
	  
	        content = null;  
	        System.out.println("加密排序后的字符串："+tmpStr);
	        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
	        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
	    }  
	 
	 
	 private static String byteToStr(byte[] byteArray) {  
	        String strDigest = "";  
	        for (int i = 0; i < byteArray.length; i++) {  
	            strDigest += byteToHexStr(byteArray[i]);  
	        }  
	        return strDigest;  
	    }  
	 
	 private static String byteToHexStr(byte mByte) {  
	        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
	        char[] tempArr = new char[2];  
	        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
	        tempArr[1] = Digit[mByte & 0X0F];  
	  
	        String s = new String(tempArr);  
	        return s;  
	    }  
	
}
