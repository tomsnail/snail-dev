package cn.tomsnail.util.string;

/**
 * 
 * 检测密码的合法性
 * 规则说明： 1.密码不能含有空格字符串
 *        2.密码只能包括字母和数字
 *
 */
public class PasswordCheckUtil {

	public static void main(String[] args) {
		String password = "123456";
		if (true == isValid(password)) {
			System.out.println("合法密码格式");
		} else {
			System.out.println("不合法密码格式");
		}
	}

	  
	   /**
		*        检查密码是否合法
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月12日 下午4:14:06
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static boolean isValid(String password) {
		if (password == null) {
			return false;
		}
		if (password.length() > 5 && password.length() < 21) {
			// 判断是否有空格字符串
			if (password.contains(" ")) {
				return false;
			}
			// 判断是否有汉字
			// 根据Unicode编码完美的判断中文汉字和符号
			char[] ch = password.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				char c = ch[i];
				Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
				if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
						|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
						|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
						|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
						|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
						|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
						|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
}