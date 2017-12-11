package cn.tomsnail.framwork.validator;

import java.util.Map;

/**
 *        自定义验证
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月8日 上午10:33:58
 * @see 
 */
public interface CustValidator {

	/**
	 *        验证
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午4:07:22
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	public boolean validator(Map<String, Object> valueMap);
	
}
