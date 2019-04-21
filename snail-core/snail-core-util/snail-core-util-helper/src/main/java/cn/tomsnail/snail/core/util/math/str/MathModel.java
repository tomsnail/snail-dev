package cn.tomsnail.snail.core.util.math.str;

public class MathModel {

	private MathDefine name;
	
	private String value;
	
	/**
	 * 杨松
	 * @return the name
	 */
	public MathDefine getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(MathDefine name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public String calculate(String param0,String param1){
		if(name.equals(MathDefine.ADD)){
			return (Double.valueOf(param0)+Double.valueOf(param1))+"";
		}else if(name.equals(MathDefine.DEL)){
			return (Double.valueOf(param0)-Double.valueOf(param1))+"";		
		}else if(name.equals(MathDefine.DIV)){
			return (Double.valueOf(param0)/Double.valueOf(param1))+"";	
		}else if(name.equals(MathDefine.MUL)){
			return (Double.valueOf(param0)*Double.valueOf(param1))+"";
		}else{
			System.out.println(param0+":"+param1);
			return "0.0D";
		}
	}

}

