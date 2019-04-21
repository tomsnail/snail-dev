package cn.tomsnail.snail.core.util.math;


import org.apache.commons.lang.StringUtils;

import cn.tomsnail.snail.core.util.math.encrypt.HmacSha1Util;
import cn.tomsnail.snail.core.util.math.encrypt.MD5Util;




public class SignUtil {

	public static boolean validSign(String sign, String... params) {

		if (params == null || params.length < 1) {
			return false;
		}
		if (StringUtils.isBlank(sign)) {
			return false;
		}
		StringBuffer ps = new StringBuffer();
		for (String param : params) {
			ps.append(param);
		}
		try {
			String md5 = MD5Util.md5Encode(ps.toString().toUpperCase());
			if (sign.equalsIgnoreCase(md5)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}
	
	public static boolean validSignHmac(String sign,String ts_sign, String... params) {

		if (params == null || params.length < 1) {
			return false;
		}
		if (StringUtils.isBlank(sign)) {
			return false;
		}
		StringBuffer ps = new StringBuffer();
		for (String param : params) {
			ps.append(param);
		}
		try {
			System.out.println(ps);
			String _sign = HmacSha1Util.getSoureSignature(ps.toString().getBytes(), ts_sign.getBytes());
			System.out.println(_sign);
			if (sign.equalsIgnoreCase(_sign)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}
	

	public static void main(String[] args) {
//		System.out.println(HmacSha1Util.getSoureSignature("29838526391527749295146".getBytes(), "b4e2cf20-697d-4ddc-bc33-9734867ecf15".getBytes()));
//		System.out.println("618c94509a7aadbaf10ff7399f3efdc2e68124c7".equals("618c94509a7aadbaf10ff7399f3efdc2e68124c7"));
//		System.out.println(validSignHmac("618c94509a7aadbaf10ff7399f3efdc2e68124c7","b4e2cf20-697d-4ddc-bc33-9734867ecf15","2983852639","1527749295146"));
//		System.out.println(HmacSha1Util.getSoureSignature
//				(
//						(
//						"1519974653478"
//						+
//						"{'O_CODE':'100001','O_NAME':'test','PARENT_CODE':'100000'}"  //当IS_SIGN为0时不加入body内容
//						+
//						"1519974653"
//						) .getBytes()
//						,
//						"13dd2177a209aaeb1c5c6f88ebd267a0d3bffb8ebb50f13c55e83917fa3cc19a".getBytes()
//						,HmacSha1Util.HMAC_SHA256)
//);
	}

}
