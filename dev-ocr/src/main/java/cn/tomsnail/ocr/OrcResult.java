package cn.tomsnail.ocr;

import java.util.Map;

public class OrcResult {

	private boolean success;
	
	private int status;
	
	private String errorDesc;
	
	private Map<String,Object> result;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	public static OrcResult NullError(){
		return new OrcResult(false,-1,"null error");
	}
	
	public static OrcResult Error(){
		return new OrcResult(false,-1,"error");
	}

	public OrcResult() {
		super();
	}

	public OrcResult(boolean success, int status, String errorDesc) {
		super();
		this.success = success;
		this.status = status;
		this.errorDesc = errorDesc;
	}
	
	
	
}
