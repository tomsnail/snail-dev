package cn.tomsnail.snail.e3.tx.weixin.util.redpackets;

public interface RedPacketsOper {
	
	
	String sendRedPackets = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	
	String sendGroupRedPackets = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack";
	
	public boolean sendRedPackets(RedPackets redPackets);
	
	public boolean sendGroupRedPackets(GroupRedPackets groupRedPackets);
	 
	
}
