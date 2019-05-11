package cn.tomsnail.snail.core.util.ex;

public class ExceptionMsg {
	
	
	public final static ExceptionMsg PARAM_ERROR  = new ExceptionMsg(2, "PARAM ERROR");
	
	private int code;
	
	private String msg;
	
	private StringBuffer desc = new StringBuffer();

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ExceptionMsg(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public ExceptionMsg() {
		super();
	}
	
	public void addDesc(String desc){
		this.desc.append(desc);
	}
	
	public String getDesc(){
		return this.desc.toString();
	}
	
	public static ExceptionMsg getInstance(int code,String msg){
		return new ExceptionMsg(code, msg);
	}
	

}
