package cn.tomsnail.framwork.validator.handler;

public class ValidResult {
	
	
	public static final ValidResult OK = new ValidResult();
	
	
	public static final ValidResult ERROR = new ValidResult(1001,"VALID FAILD");
	
	private boolean success = true;
	
	private int errorCode;
	
	private String errorDesc;

	public boolean isSuccess() {
		return success;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public ValidResult(boolean success, int errorCode, String errorDesc) {
		this.success = success;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public ValidResult(int errorCode, String errorDesc) {
		if(errorCode!=0){
			this.success = false;
		}
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}
	
	public ValidResult() {
		
	}
	
	public static ValidResult error(String errorDesc){
		return new ValidResult(1002, errorDesc);
	}

}
