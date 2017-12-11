package cn.tomsnail.auth.token;

@FunctionalInterface
public interface TicketSum {
	String sum(String info);
}
