package cn.tomsnail.snail.ext.security.token;

@FunctionalInterface
public interface TicketSum {
	String sum(String info);
}
