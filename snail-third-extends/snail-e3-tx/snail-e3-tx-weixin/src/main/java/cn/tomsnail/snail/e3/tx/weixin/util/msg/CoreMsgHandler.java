package cn.tomsnail.snail.e3.tx.weixin.util.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.e3.tx.weixin.util.core.MessageData;
import cn.tomsnail.snail.e3.tx.weixin.util.core.MessageType;

@Component
public class CoreMsgHandler {
	
	@Autowired
	private ListMsgHandler handler;

	public BasicMsg handler(MessageData messageBody) {
		BasicMsg msg = null;
		MessageType mt;
		try {
			mt = MessageType.valueOf(messageBody.getValues().get("msgType"));
		} catch (Exception e) {
			mt = MessageType.def;
		}
		
		switch (mt) {
		case text:
			msg= new TextMsg(messageBody.getValues());
			break;
		case image:
			msg = new ImageMsg(messageBody.getValues());
			break;
		case voice:
			msg = new VoiceMsg(messageBody.getValues());
			break;
		case video:
			msg = new VideoMsg(messageBody.getValues());
			break;
		case shortvideo:
			msg = new VideoMsg(messageBody.getValues());
			break;
		case location:
			msg = new LocationMsg(messageBody.getValues());
			break;
		case link:
			msg = new LinkMsg(messageBody.getValues());
			break;
		default:
			msg = new BasicMsg(messageBody.getValues());
			break;
		}
		return handler.handler(msg);
	}

}
