package cn.tomsnail.obj.base;

import java.io.Serializable;

public class BaseReturnData<T> implements Serializable{
	
	

	protected int status = 0;
	
	protected T data;
	
	protected String errorDesc;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isNormal(){
		return status==0;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	
	
	public BaseReturnData() {
	}

	public BaseReturnData(T data) {
		this.data = data;
	}
	
	public BaseReturnData(T data,int status) {
		this.data = data;
		this.status = status;
	}
	

	public BaseReturnData(T data,int status,String errorDesc) {
		this.data = data;
		this.status = status;
		this.errorDesc = errorDesc;
	}

	public static BaseReturnData<String> instance(String data){
		return new BaseReturnData<String>(data);
	}
	
	public static <T> BaseReturnData<T> instance(T data){
		return new BaseReturnData<T>(data);
	}
	
	public static <T> BaseReturnData<T> instance(T data,int status){
		return new BaseReturnData<T>(data,status);
	}
	
	public static <T> BaseReturnData<T> error(T data,int status,String errorDesc){
		return new BaseReturnData<T>(data,status,errorDesc);
	}
	
	public static boolean normal(BaseReturnData baseReturnData){
		return baseReturnData!=null&&baseReturnData.isNormal();
	}
	
	public static String errorDesc(BaseReturnData baseReturnData){
		if(baseReturnData==null){
			return null;
		}
		return baseReturnData.getErrorDesc();
	}
	

}
