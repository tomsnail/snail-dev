package cn.tomsnail.util.math;


import org.apache.commons.lang.StringUtils;

import cn.tomsnail.util.math.encrypt.Base64Util;
import cn.tomsnail.util.math.encrypt.HmacSha1Util;
import cn.tomsnail.util.math.encrypt.MD5Util;


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
			String _sign = HmacSha1Util.getSoureSignature(ps.toString().getBytes(), ts_sign.getBytes());
			if (sign.equalsIgnoreCase(_sign)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}
	

	public static void main(String[] args) {
		//System.out.println(validSign("583796cce3cad9ae1d45d0548bf5fcdd","152031568","1520315680","984fa615-3774-4cfe-8937-642aef86aeb8"));
		System.out.println(HmacSha1Util.getSoureSignature
				(
						(
						"1519974653478"
						+
						"{'O_CODE':'100001','O_NAME':'test','PARENT_CODE':'100000'}"  //当IS_SIGN为0时不加入body内容
						+
						"1519974653"
						) .getBytes()
						,
						"13dd2177a209aaeb1c5c6f88ebd267a0d3bffb8ebb50f13c55e83917fa3cc19a".getBytes()
						,HmacSha1Util.HMAC_SHA256)
);
	}

}
