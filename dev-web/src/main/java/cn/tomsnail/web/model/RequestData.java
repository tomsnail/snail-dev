package cn.tomsnail.web.model;




/**
 * 接收数据对象
 * Title: ResultData.java    
 * Description: TODO
 * @author zhanghl       
 * @date 2015年8月18日 下午2:34:10
 */
public class RequestData<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 4917480918640310535L;
	private String token;
    private String command ;
    private String sequenceID = "";
    private String checkDigit = "";
    private T body;

	public RequestData() {
	}
	
	public String getCommand() {
		return command;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getSequenceID() {
		return sequenceID;
	}

	public void setSequenceID(String sequenceID) {
		this.sequenceID = sequenceID;
	}

	public String getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	
}
