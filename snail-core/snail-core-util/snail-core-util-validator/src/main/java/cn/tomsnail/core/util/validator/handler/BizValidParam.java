package cn.tomsnail.core.util.validator.handler;

public class BizValidParam {
	
	private ValidHandler validHandler;
	
	private Object value;
	
	private ValidResult validResult;

	public ValidHandler getValidHandler() {
		return validHandler;
	}

	public void setValidHandler(ValidHandler validHandler) {
		this.validHandler = validHandler;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public BizValidParam(ValidHandler validHandler, Object value) {
		super();
		this.validHandler = validHandler;
		this.value = value;
	}

	public ValidResult getValidResult() {
		return validResult;
	}

	public void setValidResult(ValidResult validResult) {
		this.validResult = validResult;
	}
	
	

}
