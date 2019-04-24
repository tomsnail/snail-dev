package cn.tomsnail.snail.e3.tx.weixin.util.event;

import cn.tomsnail.snail.e3.tx.weixin.util.msg.BasicMsg;

public interface IEventHandler {

	public BasicMsg handler(BasicEvent event);
	
}
