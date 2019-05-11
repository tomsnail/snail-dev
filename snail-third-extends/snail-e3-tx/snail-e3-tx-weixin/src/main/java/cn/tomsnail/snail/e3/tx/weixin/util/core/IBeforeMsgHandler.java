package cn.tomsnail.snail.e3.tx.weixin.util.core;


public interface IBeforeMsgHandler {

	public boolean handler(MessageData body);
	
}
