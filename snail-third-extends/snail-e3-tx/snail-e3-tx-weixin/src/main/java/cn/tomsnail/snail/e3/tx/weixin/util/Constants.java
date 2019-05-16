package cn.tomsnail.snail.e3.tx.weixin.util;

import cn.tomsnail.snail.core.util.configfile.ConfigHelp;

public class Constants {

	public final static String TOKEN = ConfigHelp.getInstance("config").getLocalConfig("TOKEN", "weixintest01");
	
	public static String ACCESS_TOKEN = "";
	
	public static final String appSecret = ConfigHelp.getInstance("config").getLocalConfig("appSecret", "772312398e2f1b86544773a482120fe4");
	
	public static final String AESKey = ConfigHelp.getInstance("config").getLocalConfig("AESKey", "");
	
	public static final String DEFAULT_TEXT_MSG = ConfigHelp.getInstance("config").getLocalConfig("DEFAULT_TEXT_MSG", "您好,请问有什么可以帮助你的吗？");
	
	public static String HUANYINGYU = ConfigHelp.getInstance("config").getLocalConfig("HUANYINGYU", "谢谢关注");
	
	public static final String MCH_ID = ConfigHelp.getInstance("config").getLocalConfig("MCH_ID", "");
	
	public static final String WXAPPID = ConfigHelp.getInstance("config").getLocalConfig("WXAPPID", "wx39ba5e85c8d8c3db");
	
	public static final String MCH_NAME = ConfigHelp.getInstance("config").getLocalConfig("MCH_NAME", "");
	
	public static final String MCH_KEY = ConfigHelp.getInstance("config").getLocalConfig("MCH_KEY", "");
	
}
