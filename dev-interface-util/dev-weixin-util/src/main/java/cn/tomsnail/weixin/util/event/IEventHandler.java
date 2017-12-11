package cn.tomsnail.weixin.util.event;

import cn.tomsnail.weixin.util.msg.BasicMsg;

public interface IEventHandler {

	public BasicMsg handler(BasicEvent event);
	
}
