package cn.tomsnail.util.math;


import org.apache.commons.lang.StringUtils;

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
			System.out.println(md5);
			if (sign.equalsIgnoreCase(md5)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}
	

	public static void main(String[] args) {
		System.out.println(validSign("583796cce3cad9ae1d45d0548bf5fcdd","152031568","1520315680","bcb3ff58-2eec-4dff-b2c9-01a56c52e411"));
	}

}
