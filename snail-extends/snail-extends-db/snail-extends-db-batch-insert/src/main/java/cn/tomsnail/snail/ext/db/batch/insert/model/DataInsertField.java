package cn.tomsnail.snail.ext.db.batch.insert.model;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年5月24日 下午6:55:38
 * @see 
 */
public class DataInsertField {

	private String name;
	
	private String value;
	
	private int type = 0;
	
	public DataInsertField() {
	}

	public DataInsertField(String name, String value, String typeClassName) {
		super();
		this.name = name;
		this.value = value;
		this._setType(typeClassName);
	}
	
	

	public DataInsertField(String name, String value, int type) {
		super();
		this.name = name;
		this.value = value;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public Object parseValue(){
		try {
			switch(type){
				case 0:return new String(value);
				case 1:return new Double(value);
				case 2:return new Integer(value);
				case 3:return new Float(value);
				default:return new String(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void _setType(String typeClassName){
		if(typeClassName!=null){
			if(typeClassName.equals("java.lang.String")){
				type = 0;
			}
			if(typeClassName.equals("java.lang.Double")){
				type = 1;
			}
			if(typeClassName.equals("java.lang.Integer")){
				type = 2;
			}
			if(typeClassName.equals("java.lang.Float")){
				type = 3;
			}
		}
	}
	
	
}
