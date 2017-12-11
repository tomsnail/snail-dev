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
			if (sign.equalsIgnoreCase(md5)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}


}
