package cn.tomsnail.snail.e3.tx.weixin.util.common;

import java.security.MessageDigest;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SecUtil {

	public static String sgin(String key){
		return null;
	}
	
	public static String getNonceStr(){
		ThreadLocalRandom random =  ThreadLocalRandom.current();
		return MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}
	
	
	
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = String.valueOf(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname)){
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			}
			else{
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
			}

		} catch (Exception exception) {
		}
		return resultString;
	}
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++){
			resultSb.append(byteToHexString(b[i]));
		}


		return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
		"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
}
