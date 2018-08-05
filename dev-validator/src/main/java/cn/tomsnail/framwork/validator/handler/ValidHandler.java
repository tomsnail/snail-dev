package cn.tomsnail.framwork.validator.handler;

public interface ValidHandler<T> {

	public ValidResult validate(T t);
	
}
