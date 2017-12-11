package cn.tomsnail.batch.insert.model;

import java.util.Iterator;
import java.util.LinkedHashMap;

import cn.tomsnail.batch.insert.obj.annotation.BatchModel;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年5月24日 下午6:53:46
 * @see 
 */
public class DataInsertModel {
	
	private String table;
	
	private BatchModel batchModel;
	
	private LinkedHashMap<String,DataInsertField> fieldMap = new LinkedHashMap<String, DataInsertField>();

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public LinkedHashMap<String, DataInsertField> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(LinkedHashMap<String, DataInsertField> fieldMap) {
		this.fieldMap = fieldMap;
	}
	
	public void addField(String name, DataInsertField dataInsertField){
		fieldMap.put(name, dataInsertField);
	}
	
	public StringBuffer getPsSQL(){
		StringBuffer sb = new StringBuffer(" insert into ");
		sb.append(table).append(" (");
		StringBuffer sfname = new StringBuffer();
		StringBuffer svalue = new StringBuffer();
		Iterator<String> it = fieldMap.keySet().iterator();
		while(it.hasNext()){
			String fname = it.next();
			sfname.append(fname).append(",");
			svalue.append(" ").append("?").append(" ,");
		}
		if(sfname.length()<1||svalue.length()<1){
			return null;
		}
		sb.append(sfname.deleteCharAt(sfname.length()-1)).append(" ) ").append(" values ").append("(");
		sb.append(svalue.deleteCharAt(svalue.length()-1)).append(" ) ");
		return sb;
	}
	
	public StringBuffer getSQL(){
		StringBuffer sb = new StringBuffer(" insert into ");
		sb.append(table).append(" (");
		StringBuffer sfname = new StringBuffer();
		StringBuffer svalue = new StringBuffer();
		Iterator<String> it = fieldMap.keySet().iterator();
		while(it.hasNext()){
			String fname = it.next();
			DataInsertField insertField = fieldMap.get(fname);
			sfname.append(fname).append(",");
			if(insertField.parseValue().equals("NULL")){
				svalue.append(" ").append(insertField.parseValue()).append(" ,");
			}else{
				svalue.append(" '").append(insertField.parseValue()).append("' ,");

			}
		}
		if(sfname.length()<1||svalue.length()<1){
			return null;
		}
		sb.append(sfname.deleteCharAt(sfname.length()-1)).append(" ) ").append(" values ").append("(");
		sb.append(svalue.deleteCharAt(svalue.length()-1)).append(" ) ");
		return sb;
	}

	public BatchModel getBatchModel() {
		return batchModel;
	}

	public void setBatchModel(BatchModel batchModel) {
		this.batchModel = batchModel;
	}
	
}
