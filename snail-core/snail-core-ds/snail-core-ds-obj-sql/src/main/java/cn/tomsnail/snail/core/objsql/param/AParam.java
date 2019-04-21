package cn.tomsnail.snail.core.objsql.param;

import java.io.Serializable;


/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月23日 上午11:42:15
 * @see 
 */
public abstract class AParam implements IParam,Serializable{

	protected ParamType paramType;
	
	protected int index = 0;
	
	protected String name;
	
	protected String alias = null;
	
	protected IParam nextParam;
	
	public AParam(ParamType paramType, int index,String alias){
		this.paramType = paramType;
		this.index = index;
		this.alias = alias;
	}
	
	public AParam(ParamType paramType,IParam nextParam){
		this.paramType = paramType;
		this.nextParam = nextParam;
	}

	public ParamType getParamType() {
		return paramType;
	}

	public void setParamType(ParamType paramType) {
		this.paramType = paramType;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public AParam add(IParam param){
		if(nextParam!=null){
			nextParam.add(param);
		}else{
			nextParam = param;
		}
		return this;
	}
	
}
