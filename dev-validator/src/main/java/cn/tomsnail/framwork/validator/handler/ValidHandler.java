package cn.tomsnail.framwork.validator.handler;

@FunctionalInterface
public interface ValidHandler<T> {

	public ValidResult validate(T t);
	
}
