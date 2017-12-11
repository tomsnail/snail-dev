package cn.tomsnail.weixin.util.msg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.tomsnail.weixin.util.Constants;

@Component
public class ListMsgHandler implements IMsgHandler{

	@Autowired
	private List<IMsgHandler> handlers;
	
	public BasicMsg handler(BasicMsg msg) {
		
		if(handlers!=null){
			for(IMsgHandler msgHandler:handlers){
				BasicMsg retMsg = msgHandler.handler(msg);
				if(retMsg!=null){
					return retMsg;
				}
			}
		}
		
		return getDefaultMsg(msg);
	}
	
	public static BasicMsg getDefaultMsg(BasicMsg msg){
		TextMsg textMsg = new TextMsg(msg);
		textMsg.setContent(Constants.DEFAULT_TEXT_MSG);
		return textMsg;
	}

}
