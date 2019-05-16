package cn.tomsnail.snail.core.obj.base;

import cn.tomsnail.snail.core.http.ResultData;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import org.apache.commons.collections.map.LRUMap;

import java.io.Serializable;


public class BaseReturnData<T> implements Serializable{
	
	private static final LRUMap LRU_MAP  = new LRUMap(256);
	
	protected int status = 0;
	
	protected T data;
	
	protected String errorDesc;
	
	protected String desc;

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
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
	public BaseReturnData() {
	}

	public BaseReturnData(T data) {
		this.data = data;
	}
	
	public BaseReturnData(T data,String desc) {
		this.data = data;
		this.desc = desc;
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
	
	public static <T> BaseReturnData<T> instance(T data,String desc){
		return new BaseReturnData<T>(data,desc);
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
	
	public static <T> BaseReturnData<T> error(int status,String errorDesc){
		return error(null,status,errorDesc);
	}
	
	public static <T> BaseReturnData<T> error(String key){
				
		if(!LRU_MAP.containsKey(key)){
			int status = -1;
			try {
				status = Integer.parseInt(ConfigHelp.getInstance("config").getLocalConfig("message."+key+".status", "-1"));
			} catch (NumberFormatException e) {
			}
			String errorDesc = ConfigHelp.getInstance("config").getLocalConfig("message."+key+".message", "");
			BaseReturnData returnData =  error(null,status,errorDesc);
			LRU_MAP.put(key, returnData);
		}
		
		return (BaseReturnData) LRU_MAP.get(key);
		
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
	
	 public ResultData getResultData(){
		 ResultData resultData = new ResultData();
		 resultData.setBody(this.getData());
		 resultData.setStatus(this.getStatus()+"");
		 resultData.setErrorMsg(this.getErrorDesc());
	     return resultData;
	 }

}
