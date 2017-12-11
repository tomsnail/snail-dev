package cn.tomsnail.objsql.util;

import cn.tomsnail.objsql.IBasicObjectSQL;
import cn.tomsnail.objsql.ObjectSQL;
import cn.tomsnail.objsql.db.IDBoper;

  
   /**
	*        
	* @author yangsong
	* @version 0.0.1
	* @status 正常
	* @date 2016年12月19日 下午12:08:54
	* @see 
	*/     
  
public abstract class AObjectSQL<T> implements IBasicObjectSQL<T>{
	
	protected IDBoper dbOper;

	protected String className;
	
	protected int start = 0;
	
	protected int limit = 10;
	
	protected ObjectSQL objectSQL = null;
	
	protected Class clazz;

	public AObjectSQL(String className) {
		super();
		this.className = className;
		this.objectSQL = objectSQL();
	}

	public IDBoper getDbOper() {
		return dbOper;
	}

	public void setDbOper(IDBoper dbOper) {
		this.dbOper = dbOper;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	protected ObjectSQL objectSQL(){
		
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		if(clazz.isAnnotationPresent(cn.tomsnail.objsql.annotation.ObjectSQL.class)){
			cn.tomsnail.objsql.annotation.ObjectSQL objectSQLA = (cn.tomsnail.objsql.annotation.ObjectSQL) clazz.getAnnotation(cn.tomsnail.objsql.annotation.ObjectSQL.class);
			String tablename = "";
			
			if(objectSQLA.tablename().equals("default")||objectSQLA.tablename().equals("")){
				tablename = clazz.getSimpleName().toLowerCase();
			}else{
				tablename = objectSQLA.tablename();
			}
			ObjectSQL objectSQL = new ObjectSQL(objectSQLA.idname(), tablename);
			objectSQL.setClassName(clazz.getName());
			return objectSQL;
		}
		return null;
	}

	public ObjectSQL getObjectSQL() {
		return objectSQL;
	}

	public void setObjectSQL(ObjectSQL objectSQL) {
		this.objectSQL = objectSQL;
	}
	
	
	
}
