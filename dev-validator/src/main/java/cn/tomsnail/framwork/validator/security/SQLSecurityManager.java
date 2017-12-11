package cn.tomsnail.framwork.validator.security;

public class SQLSecurityManager {

	private static final String inj_str = "'|insert|select|delete|update|chr|mid|truncate|declare|or";

	public static boolean sql_inj(String str) {
		String[] inj_stra = inj_str.split("\\|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf("" + inj_stra[i] + " ") >= 0) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String t = " or '1' = '1'";
		System.out.println(sql_inj(t));
	}
}
