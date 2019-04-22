package cn.tomsnail.snail.ext.security.token;

@FunctionalInterface
public interface TokenValitor {
	boolean valitor(CacheData result);
}
