package cn.tomsnail.framwork.e;

public class DataValidException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8097867946439461184L;

	public DataValidException() {
		super();
	}

	public DataValidException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataValidException(String message) {
		super(message);
	}

	public DataValidException(Throwable cause) {
		super(cause);
	}
	
	

}
