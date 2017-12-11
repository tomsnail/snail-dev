package cn.tomsnail.web.model;

/**
 * 封装返回数据对象
 * Title: ResultData.java    
 * Description: TODO
 * @author zhanghl       
 * @date 2015年8月18日 下午2:34:10
 */
public class ResultData<T> implements java.io.Serializable {
	
	private static final long serialVersionUID = 4917480918640310535L;
    private String command ;
    private String sequenceID = "";
    private String checkDigit = "";
    private T data;
    private String result = CommonMessage.SUCCESS;//消息状态
    private String message = "查询成功";//消息内容
	public ResultData() {
	}
	
	public String getCommand() {
		return command+"Resp";
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
     * @discription 判断返回状态是否成功
     * @author zhanghl       
     * @date 2015年11月3日 下午5:31:50     
     * @return
	 */
	public boolean isSuccessful() {
		return CommonMessage.SUCCESS.equals(this.result);
	}
}
