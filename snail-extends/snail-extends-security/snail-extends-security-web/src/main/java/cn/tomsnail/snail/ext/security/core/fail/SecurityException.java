package cn.tomsnail.snail.ext.security.core.fail;

/**
 *        安全异常类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月9日 下午3:19:16
 * @see 
 */
public class SecurityException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3805564666408354674L;

	public SecurityException(String msg){
		super(msg);
	}
}
