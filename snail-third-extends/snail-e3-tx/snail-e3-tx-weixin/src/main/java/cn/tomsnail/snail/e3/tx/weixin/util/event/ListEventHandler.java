package cn.tomsnail.snail.e3.tx.weixin.util.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.e3.tx.weixin.util.Constants;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.BasicMsg;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.TextMsg;

@Component
public class ListEventHandler implements IEventHandler{

	@Autowired
	private List<IEventHandler> handlers;
	
	public BasicMsg handler(BasicEvent event) {
		
		if(handlers!=null){
			for(IEventHandler handler:handlers){
				BasicMsg retMsg = handler.handler(event);
				if(retMsg!=null){
					return retMsg;
				}
			}
		}
		
		return getDefaultMsg();
	}
	
	public static BasicMsg getDefaultMsg(){
		TextMsg textMsg = new TextMsg();
		textMsg.setContent(Constants.DEFAULT_TEXT_MSG);
		return textMsg;
	}

}
