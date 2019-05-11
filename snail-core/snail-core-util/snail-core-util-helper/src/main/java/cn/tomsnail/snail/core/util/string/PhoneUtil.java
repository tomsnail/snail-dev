package cn.tomsnail.snail.core.util.string;

import org.apache.commons.lang.StringUtils;

import cn.tomsnail.core.util.validator.util.CommonValidatorUtil;


  
   /**
	*        电话常用类
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2017年9月12日 下午4:14:27
	* @see 
	*/     
public class PhoneUtil {
	
	
	public final static String MASK = "*";

	  
	   /**
		*        姓名掩码
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月12日 下午4:14:47
		* @see 
		* @param                   
		* @return               
		* @status 正常
		* @exception no
		*/
		
	public static String getMobileMaskStr(String phone){
		return getMobileMaskStr(3,3,phone,MASK);
	}
	
	  
	   /**
		*        姓名掩码
		* @methodauthor yangsong
		* @methodversion 0.0.1
		* @date 2017年9月12日 下午4:16:01
		* @see 
		* @param   first  保留头几位
		* @param   last   保留尾几位
		* @param   phone  电话号码
		* @param   mask     填充内容
		* @return               
		* @status 正常
		* @exception no
		*/
	public static String getMobileMaskStr(int first,int last,String phone,String mask){
		
		if(StringUtils.isBlank(phone)){
			return "";
		}
		if(StringUtils.isBlank(mask)){
			mask = MASK;
		}
		StringBuffer sb = new StringBuffer();
		int l = 11-first-last;
		for(int i=0;i<l;i++){
			sb.append(mask);
		}
		if(!CommonValidatorUtil.validator(phone, CommonValidatorUtil.MOBILE)){
			return phone;
		}
		return phone.substring(0, first) + sb + phone.substring(11-last, phone.length());
	}
	
	public static void main(String[] args) {
		System.out.println(getMobileMaskStr(3,3,"15942332392","#@"));
	}
}
