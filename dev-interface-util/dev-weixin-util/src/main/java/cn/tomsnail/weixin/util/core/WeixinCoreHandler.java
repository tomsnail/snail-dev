package cn.tomsnail.weixin.util.core;



import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.tomsnail.weixin.util.access.AccessCheck;
import cn.tomsnail.weixin.util.common.XmlMsgBuilder;
import cn.tomsnail.weixin.util.event.CoreEventHandler;
import cn.tomsnail.weixin.util.msg.BasicMsg;
import cn.tomsnail.weixin.util.msg.CoreMsgHandler;
import cn.tomsnail.weixin.util.msg.CustomerServiceMsg;
import cn.tomsnail.weixin.util.msg.ImageMsg;
import cn.tomsnail.weixin.util.msg.MusicMsg;
import cn.tomsnail.weixin.util.msg.NewsMsg;
import cn.tomsnail.weixin.util.msg.TextMsg;
import cn.tomsnail.weixin.util.msg.VideoMsg;
import cn.tomsnail.weixin.util.msg.VoiceMsg;

@Component
@ComponentScan(basePackages={"cn.tomsnail.weixin.util.msg","cn.tomsnail.weixin.util.event"})
public class WeixinCoreHandler {

	@Autowired
	private CoreMsgHandler msgHandler;
	
	@Autowired
	private CoreEventHandler eventHandler;
	
	@Autowired(required=false)
	private IBeforeMsgHandler beforeMsgHandler;
	

	public String check(String signature, String timestramp, String nonce, String echostr) {
		return AccessCheck.check(signature, timestramp, nonce, echostr);
	}

	public String handler(MessageData body) {
		
		if(beforeMsgHandler!=null){
			if(!beforeMsgHandler.handler(body)){
				return "error";
			}
		}
		
		try {
			String msgtype = body.getValues().get("msgType");
			BasicMsg msg = null;
			if ("event".equals(msgtype)) {
				msg = eventHandler.handler(body);
			}
			else 
				msg =  msgHandler.handler(body);
			return responseXML(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	protected String responseXML(BasicMsg msg) {
        String respmsg = "success";
        if (msg == null || StringUtils.isBlank(msg.getMsgType())) {
            return respmsg;
        }

        // 交换 fromUser 和 toUser
        String fromUser = msg.getFromUserName();
        String toUser = msg.getToUserName();
        msg.setFromUserName(toUser);
        msg.setToUserName(fromUser);

        MessageType mt = MessageType.valueOf(msg.getMsgType());

        switch (mt) {
            case text:
                respmsg = XmlMsgBuilder.create().text((TextMsg) msg).build();
                break;
            case image:
                respmsg = XmlMsgBuilder.create().image((ImageMsg) msg).build();
                break;
            case voice:
                respmsg = XmlMsgBuilder.create().voice((VoiceMsg) msg).build();
                break;
            case music:
                respmsg = XmlMsgBuilder.create().music((MusicMsg) msg).build();
                break;
            case video:
                respmsg = XmlMsgBuilder.create().video((VideoMsg) msg).build();
                break;
            case news:
                respmsg = XmlMsgBuilder.create().news((NewsMsg) msg).build();
                break;
            case transfer_customer_service:
                respmsg = XmlMsgBuilder.create().transferCustomerService((CustomerServiceMsg) msg).build();
                break;
            default:
                break;
        }
        return respmsg;
    }

}
