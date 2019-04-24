package cn.tomsnail.snail.e3.tx.weixin.util.redpackets;

public class GroupRedPackets extends RedPackets{

	/**
	 * 金额设置方式：全部随机
	 */
	private String amt_type = "ALL_RAND";

	public String getAmt_type() {
		return amt_type;
	}

	public void setAmt_type(String amt_type) {
		this.amt_type = amt_type;
	}
	
	
	
}
