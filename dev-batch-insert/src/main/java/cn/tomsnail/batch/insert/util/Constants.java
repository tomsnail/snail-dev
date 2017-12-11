package cn.tomsnail.batch.insert.util;

import cn.tomsnail.util.configfile.ConfigHelp;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月20日 下午1:59:46
	* @see 
	*/     
public class Constants {

	public static  int coreSize = get("batchinsert.coreSize",5);
	
	public static  int maxSize = get("batchinsert.maxSize",10);
	
	public static  int zeroCount = get("batchinsert.zeroCount",0);
	
	public static  boolean isRun = get("batchinsert.isRun",true);
	
	public static  int dbSize = get("batchinsert.dbSize",5);
	
	public static  long insertCount  = get("batchinsert.insertCount",0l);
	
	public static <T> T get(String key,T t){
		String v = ConfigHelp.getInstance("config").getLocalConfig(key, t+"");
		if(t.getClass().getName().equals("java.lang.Integer")){
			return (T) Integer.valueOf(v);
		}
		if(t.getClass().getName().equals("java.lang.Boolean")){
			return (T) Boolean.valueOf(v);
		}
		if(t.getClass().getName().equals("java.lang.Long")){
			return (T) Long.valueOf(v);
		}
		return t;
	}
	
	public static void main(String[] args) {
	}
	
}
