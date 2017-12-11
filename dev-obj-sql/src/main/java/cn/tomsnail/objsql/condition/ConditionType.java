package cn.tomsnail.objsql.condition;

public enum ConditionType {

	eq,gt,lt,neq,gteq,lteq,order,group,in,notin,like,notlike;//in,notin,like,notlike;
	
	public String get(){
		switch(this){
			case eq:return " = ";
			case gt:return " > ";
			case lt:return " < ";
			case neq:return " <> ";
			case gteq:return " >= ";
			case lteq:return " <= ";
			case order:return " order by ";
			case group:return " group by ";
			case in:return " in ";
			case notin:return " not in ";
			case like:return " like ";
			case notlike:return " not like ";
		}
		return null;
	}
}
