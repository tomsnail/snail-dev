package cn.tomsnail.auth.token;

@FunctionalInterface
public interface TokenValitor {
	boolean valitor(CacheData result);
}
