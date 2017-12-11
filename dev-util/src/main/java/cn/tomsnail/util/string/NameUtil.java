package cn.tomsnail.util.string;

public class NameUtil {
	
	
	/**
	 * 格式化用户名，如：村***名
	 * @param userName
	 * @return
	 */
	public static String getUserNameMask(String userName) {
		if (userName == null || userName.trim().length() == 0)
			return "";

		String sRet = userName.trim();
		if (sRet.length() > 1)
			sRet = sRet.substring(0, 1) + "***" + sRet.substring(sRet.length() - 1);
		else
			sRet = "***" + sRet;
		return sRet;
	}

	/**
	 * 对姓名做掩码处理，最多保留3位
	 * 
	 * @param content
	 *            姓名的字符串
	 * @param begin
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @return
	 */
	public static String getMaskName(String content, int begin, int end) {

		if (content == null || "".equals(content)) {
			return "";
		}
		if (begin >= content.length() || begin < 0) {
			return content;
		}
		if (content.length() < end) {
			end = content.length();
		}
		if (content.length() > 3) {
			content = content.substring(0, 3);
			end = 3;
		}
		if (begin >= end) {
			return content;
		}
		String starStr = "";
		for (int i = begin; i < end; i++) {
			starStr = starStr + "*";
		}
		return content.substring(0, begin) + starStr + content.substring(end, content.length());
	}

}
