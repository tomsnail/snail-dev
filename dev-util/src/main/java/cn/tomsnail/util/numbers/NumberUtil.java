package cn.tomsnail.util.numbers;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import cn.tomsnail.framwork.validator.util.CommonValidatorUtil;
import cn.tomsnail.util.string.StringUtils;

public class NumberUtil {

	/**
	 * 数字格式化:四舍五入
	 * 
	 * @author zgl
	 * @param str
	 *            数字
	 * @param digit
	 *            保留小数点位数
	 * @return
	 */
	public static String string2StringHalfUp(String str, int digit) throws NumberFormatException {
		if (!CommonValidatorUtil.validator(str, CommonValidatorUtil.NUMBER)) {
			throw new NumberFormatException();
		}
		if (digit < 0) {
			digit = 0;
		}
		if (digit > 4) {
			digit = 4;
		}
		BigDecimal bd = new BigDecimal(str);
		return bd.setScale(digit, BigDecimal.ROUND_HALF_UP).toString();
	}

	/**
	 * 数字格式化:向上取整
	 * 
	 * @author zgl
	 * @param str
	 *            数字
	 * @param digit
	 *            保留小数点位数
	 * @return
	 */
	public static String string2StringUp(String str, int digit) throws NumberFormatException {
		if (!CommonValidatorUtil.validator(str, CommonValidatorUtil.NUMBER)) {
			throw new NumberFormatException();
		}
		if (digit < 0) {
			digit = 0;
		}
		if (digit > 4) {
			digit = 4;
		}
		BigDecimal bd = new BigDecimal(str);
		return bd.setScale(digit, BigDecimal.ROUND_UP).toString();
	}

	/**
	 * 数字格式化:向下取整
	 * 
	 * @author zgl
	 * @param str
	 *            数字
	 * @param digit
	 *            保留小数点位数
	 * @return
	 */
	public static String string2StringDown(String str, int digit) throws NumberFormatException {
		if (!CommonValidatorUtil.validator(str, CommonValidatorUtil.NUMBER)) {
			throw new NumberFormatException();
		}
		if (digit < 0) {
			digit = 0;
		}
		if (digit > 4) {
			digit = 4;
		}
		BigDecimal bd = new BigDecimal(str);
		return bd.setScale(digit, BigDecimal.ROUND_DOWN).toString();
	}
	
	   
	   /**
		*        保留两位小数
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月12日 下午4:13:24
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String amountFormatToString(Double str){
	    	if(str==null){
	    		str = new Double(0);
	    	}
	    	DecimalFormat myformat = new DecimalFormat();
	    	myformat.applyPattern("###0.00");
	    	return myformat.format(str);
	    }
	    /**
	     * @discription 金额格式化，保留小数点后两位
	     * @author zhanghl       
	     * @date 2015年10月26日 上午9:33:57     
	     * @param str
	     * @return
	     */
	    public static Double amountFormat(Double str){
	    	DecimalFormat myformat = new DecimalFormat();
	    	myformat.applyPattern("###0.00");
	    	return Double.parseDouble(myformat.format(str));
	    }

		/**
		 * 金额格式化，StringToString
		 * 
		 * @methodauthor zhanggeliang
		 * @methodversion 0.0.1
		 * @date 2015年12月30日 下午4:18:09
		 * @see
		 * @param str
		 * @return
		 * @status 正常
		 * @exception no
		 */
		public static String stringAmountFormat(String str) {
			if (StringUtils.isBlank(str)) {
				return "0.00";
			}
			double amount = 0;
			try {
				amount = Double.parseDouble(str);
			} catch (Exception e) {
				amount = 0;
			}
			return amountFormatToString(amount);
		}


}