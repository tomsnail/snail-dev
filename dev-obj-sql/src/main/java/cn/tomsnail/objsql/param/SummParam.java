package cn.tomsnail.objsql.param;

import java.io.Serializable;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年6月23日 上午11:42:57
 * @see 
 */
public class SummParam extends AParam implements Serializable{
	
	public SummParam(ParamType paramType,int index,String alias){
		super(paramType, index, alias);
	}
	
	public SummParam(ParamType paramType,IParam param){
		super(paramType, param);
	}

	@Override
	public String get() {
		StringBuffer paramStr = new StringBuffer(" ");
		if(paramType!=null){
			paramStr.append(paramType.toString()).append("(");
			if(name!=null){
				paramStr.append(name);
			}else{
				paramStr.append(index);
			}
			paramStr.append(")");
			if(alias!=null){
				paramStr.append(" as ").append(alias);
			}
		}
		if(this.nextParam!=null){
			paramStr.append(" , ").append(this.nextParam.get());
		}
		return paramStr.toString();
	}
}
