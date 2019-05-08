package cn.tomsnail.snail.core.http;




/**
 * 接收数据对象
 * Title: ResultData.java    
 * Description: TODO
 */
public class RequestData<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 4917480918640310535L;
    private String command = "";
    private String sequenceID = "";
    private String fingerprint = "";
    private T body;
    private String version = "";
    private String key= "";
    private String timestrap= "";
    private String uri= "";
    private Object targetObj;
 

	public RequestData() {
	}
	
	public String getCommand() {
		return command;
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

	

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTimestrap() {
		return timestrap;
	}

	public void setTimestrap(String timestrap) {
		this.timestrap = timestrap;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}


	public Object getTargetObj() {
		return targetObj;
	}

	public void setTargetObj(Object targetObj) {
		this.targetObj = targetObj;
	}
}
