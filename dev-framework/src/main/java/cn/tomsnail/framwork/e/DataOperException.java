package cn.tomsnail.framwork.e;

public class DataOperException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4297291171884487913L;

	public DataOperException() {
		super();
	}

	public DataOperException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataOperException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataOperException(String message) {
		super(message);
	}

	public DataOperException(Throwable cause) {
		super(cause);
	}
	
	

}
