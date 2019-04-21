package cn.tomsnail.snail.core.objsql.parse;

import java.lang.reflect.Field;

import cn.tomsnail.snail.core.objsql.ObjectSQL;
import cn.tomsnail.snail.core.objsql.param.IParam;



/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月16日 下午5:51:35
 * @see 
 */
public class BaseSqlParse extends ASqlParse{
	
	protected ObjectSQL obj;
	
	protected String[] fields;

	public BaseSqlParse(ASqlParse parse) {
		super(parse);
	}
	
	public BaseSqlParse(SQLType sqlType){
		super(null);
		this.sqlType = sqlType;
	}
	
	public BaseSqlParse(ObjectSQL obj) {
		super(null);
		this.obj = obj;
	}
	
	public BaseSqlParse(ObjectSQL obj,IParam param) {
		super(null);
		this.obj = obj;
		this.ipSql = param;
	}
	
	public BaseSqlParse(ObjectSQL obj,String[] fields) {
		super(null);
		this.obj = obj;
		this.fields = fields;
	}
	
	public BaseSqlParse(){
		super(null);
	}

	@Override
	public void parse() {
	}

	@Override
	public String getSQL() {
		String sql = "";
		switch(sqlType){
		case SELECT:
			sql=this.getSelectSQL();
			break;
		case DELETE:
			sql=this.getDeleteSQL();
			break;		
		}
		if(this.parse==null){
			return sql;
		}else{
			return this.parse.getSQL()+sql;
		}
	}
	
	public String getSQL(Object object){
		String sql = "";
		switch(this.sqlType){
		case SELECT:
			sql=this.getSelectSQL();
			break;
		case DELETE:
			sql=this.getDeleteSQL();
			break;
		case INSERT:
			try {
				sql=this.getInsertSQL(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case UPDATE:
			try {
				sql=this.getUpdateSQL(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		if(this.parse==null){
			return sql;
		}else{
			return this.parse.getSQL()+sql;
		}
	}
	
	private String getSelectSQL(){
		String sql = "";
		String paramStr = "";
		if(ipSql!=null){
			paramStr = ipSql.get();
			sql = "select "+paramStr+" from "+obj.getTablename();
		}else{
			if(fields==null){
				try {
					sql = "select "+paramStr+" "+obj.getFieldSqlStr()+" from "+obj.getTablename();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				sql = "select "+paramStr+" "+obj.getFieldSqlStr(fields)+" from "+obj.getTablename();
			}
		}
		
		return sql;
	}
	
	private String getDeleteSQL(){
		return "delete from "+obj.getTablename();
	}
	
	private String getInsertSQL(Object object) throws Exception{
		StringBuffer sql = new StringBuffer();
		Class clazz = Class.forName(obj.getClassName());
		if(fields!=null){
			sql.append("insert into ").append(this.obj.getTablename()).append(" (");
			StringBuffer values = new StringBuffer();
			for(String field:fields){
				Field f = clazz.getField(field);
				f.setAccessible(true);
				Object v = f.get(object);
				if(v==null) continue;
				sql.append(field).append(",");
				values.append("'").append(v).append("'").append(",");
			}
			values.deleteCharAt(values.length()-1);
			sql.deleteCharAt(sql.length()-1).append(") values (").append(values).append(")");
		}else{
			sql.append("insert into ").append(this.obj.getTablename()).append(" (");
			StringBuffer values = new StringBuffer();
			for(Field f:clazz.getDeclaredFields()){
				f.setAccessible(true);
				Object obj = f.get(object);
				if(obj==null) continue;
				sql.append(f.getName()).append(",");
				values.append("'").append(obj).append("'").append(",");
			}
			values.deleteCharAt(values.length()-1);
			sql.deleteCharAt(sql.length()-1).append(") values (").append(values).append(")");
		}
		return sql.toString();
	}
	
	private String getUpdateSQL(Object object) throws Exception{
		StringBuffer sql = new StringBuffer();
		Class clazz = Class.forName(obj.getClassName());
		if(fields!=null){
			sql.append("update  ").append(this.obj.getTablename()).append(" set ");
			for(String field:fields){
				Field f = clazz.getDeclaredField(field);
				f.setAccessible(true);
				Object obj = f.get(object);
				if(obj==null) continue;
				sql.append(field).append("='").append(obj).append("',");
			}
			sql.deleteCharAt(sql.length()-1).append("  ");
		}else{
			sql.append("update  ").append(this.obj.getTablename()).append(" set ");
			for(Field f:clazz.getDeclaredFields()){
				f.setAccessible(true);
				Object obj = f.get(object);
				if(obj==null) continue;
				sql.append(f.getName()).append("='").append(obj).append("',");
			}
			sql.deleteCharAt(sql.length()-1).append("  ");
		}
		return sql.toString();
	}

	public ObjectSQL getObj() {
		return obj;
	}

	public void setObj(ObjectSQL obj) {
		this.obj = obj;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	
	
}
