
  package cn.tomsnail.snail.core.objsql;

import java.lang.reflect.Field;

   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 上午11:27:11
	* @see 
	*/
public class ObjectSQL {

	private String idname;
	
	private String tablename;
	
	private String className;
		
	public ObjectSQL(String idname, String tablename) {
		super();
		this.idname = idname;
		this.tablename = tablename.toLowerCase();
	}
	
	public ObjectSQL(String className) {
		this.className = className;
	}

	public String getIdname() {
		return idname;
	}

	public void setIdname(String idname) {
		this.idname = idname;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFieldSqlStr() throws Exception{
		StringBuffer sql = new StringBuffer();
		Class clazz = Class.forName(this.className);
		sql.append("");
		for(Field f:clazz.getDeclaredFields()){
			sql.append(f.getName()).append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		return sql.toString();
	}
	
	public String getFieldSqlStr(String files[]){
		StringBuffer sql = new StringBuffer();
		sql.append("");
		for(String f:files){
			sql.append(f).append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		return sql.toString();
	}
	
}
