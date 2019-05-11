package cn.tomsnail.core.util.validator.handler;

@FunctionalInterface
public interface ValidHandler<T> {

	public ValidResult validate(T t);
	
}
