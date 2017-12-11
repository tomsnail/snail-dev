package cn.tomsnail.framwork.validator.util;

import org.apache.commons.lang3.StringUtils;

import cn.tomsnail.framwork.validator.ValidateUtil;
import cn.tomsnail.framwork.validator.security.SQLSecurityManager;

  
   /**
	*        通用验证类
	* @author yangsong
	* @version 0.0.3
	* @status 正常
	* @date 2017年9月12日 上午10:17:00
	* @see 
	*/     
public class CommonValidatorUtil {
	
	/**
	 * 数值
	 */
	public static final int NUMBER = 0;
	/**
	 * 座机
	 */
	public static final int PHONE = 1;
	/**
	 * 移动电话
	 */
	public static final int MOBILE = 2;
	/**
	 * 电子邮件
	 */
	public static final int EMAIL = 3;
	/**
	 * 身份证
	 */
	public static final int IDCARD = 4;
	/**
	 * QQ号码
	 */
	public static final int QQ = 5;
	/**
	 * 空
	 */
	public static final int NULL = 6;
	/**
	 * SQL注入
	 */
	public static final int SQL_INJ = 7;
	
	/**
	 * 验证
	 * 
	 * @param str 字符串
	 * @param type 类型
	 * @return boolean
	 */
	public static boolean validator(String str,int type){
		switch(type){
			case NUMBER: return ValidateUtil.isNumber(str);
			case PHONE: return ValidateUtil.isTelephone(str);
			case MOBILE: return ValidateUtil.isPhoneLegal(str);
			case EMAIL: return ValidateUtil.isEmail(str);
			case IDCARD: return ValidateUtil.isIdCard(str);
			case QQ: return ValidateUtil.isQQ(str);
			case NULL: return StringUtils.isBlank(str);
			case SQL_INJ: return SQLSecurityManager.sql_inj(str);
			default:return false;
		}
	}
	
	
	

}
