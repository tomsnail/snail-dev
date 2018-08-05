package cn.tomsnail.framwork.validator.exception;

/**
 *        参数验证异常类
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:04:01
 * @see 
 */
public class ParamValidatorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8587107293605520906L;
	
	public ParamValidatorException(String msg){
		super(msg);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return null;
	}
	
	

}
