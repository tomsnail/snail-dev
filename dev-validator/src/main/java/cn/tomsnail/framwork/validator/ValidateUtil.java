package cn.tomsnail.framwork.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.StringUtils;

public class ValidateUtil {
	
	
	/**
	 * 大陆号码或香港号码均可
	 */
	public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
		return isMobile(str) || isHKPhoneLegal(str);
	}

	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 此方法中前三位格式有： 13+任意数 15+除4的任意数 18+除1和4的任意数
	 * 17+除9的任意数 147
	 */
	public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 */
	public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 验证手机号正确性
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobiles) {
		if(StringUtils.isBlank(mobiles)){
			return false;
		}
		String pattern = "^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 验证固定电话正确性
	 * 
	 * @param telephone
	 * @return
	 */
	public static boolean isTelephone(String telephone) {
		if(StringUtils.isBlank(telephone)){
			return false;
		}
		String pattern = "(?:(\\(\\+?86\\))([0-9]{3,4}\\-?)?([0-9]{7,8})+(\\-[0-9]{1,4})?)|(?:(86-?)?([0-9]{3,4}\\-?)?([0-9]{7,8})+(\\-[0-9]{1,4})?)";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(telephone);
		return m.matches();
	}
	/**
	 * 验证邮箱正确性
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if(StringUtils.isBlank(email)){
			return false;
		}
		String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	/**
	 * 验证身份证号码正确性
	 * 
	 * @param idCard
	 * @return
	 */
	public static boolean isIdCard(String idCard) {
		if(StringUtils.isBlank(idCard)){
			return false;
		}
		String pattern = "\\d{17}[\\d|x,X]|\\d{15}";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(idCard);
		return m.matches();
	}
	/**
	 * 验证QQ号码正确性
	 * 
	 * @param idCard
	 * @return
	 */
	public static boolean isQQ(String qq) {
		if(StringUtils.isBlank(qq)){
			return false;
		}
		String pattern = "-?[1-9]\\d*";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(qq);
		return m.matches();
	}
	
	public static boolean isNumber(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");	
	}
	
	public static String getValidFaildMsg(String desc,String t){
		if(StringUtils.isBlank(desc)){
			return t+"";
		}
		return desc;
	}
}
