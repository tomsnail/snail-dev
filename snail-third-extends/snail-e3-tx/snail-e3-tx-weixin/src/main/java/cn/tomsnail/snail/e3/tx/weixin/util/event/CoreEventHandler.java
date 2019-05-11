package cn.tomsnail.snail.e3.tx.weixin.util.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.e3.tx.weixin.util.core.EventType;
import cn.tomsnail.snail.e3.tx.weixin.util.core.MessageBody;
import cn.tomsnail.snail.e3.tx.weixin.util.core.MessageData;
import cn.tomsnail.snail.e3.tx.weixin.util.msg.BasicMsg;

@Component
public class CoreEventHandler {
	
	@Autowired
	private ListEventHandler handler;

	public BasicMsg handler(MessageData body){
        EventType et;
        try {
            et = EventType.valueOf(body.getValues().get("event"));
        }
        catch (Exception e) {
            et = EventType.def;
        }
        BasicEvent event;
        switch (et) {
            case subscribe:
            	event = new BasicEvent(body.getValues());
                break;
            case unsubscribe:
            	event = new BasicEvent(body.getValues());
                break;
            case SCAN:
            	event = new ScanEvent(body.getValues());
                break;
            case LOCATION:
            	event = new LocationEvent(body.getValues());
                break;
            case CLICK:
            	event = new MenuEvent(body.getValues());
                break;
            case VIEW:
            	event = new MenuEvent(body.getValues());
                break;
            case scancode_push:
            	event = new ScanCodeEvent(body.getValues());
                break;
            case scancode_waitmsg:
            	event = new ScanCodeEvent(body.getValues());
                break;
            case pic_sysphoto:
            	event = new SendPhotosEvent(body.getValues());
                break;
            case pic_photo_or_album:
            	event = new SendPhotosEvent(body.getValues());
                break;
            case pic_weixin:
            	event = new SendPhotosEvent(body.getValues());
                break;
            case location_select:
            	event = new SendLocationInfoEvent(body.getValues());
                break;
            // TODO 暂不清楚微信的推送
            /*
             * case media_id:
             * case view_limited:
             * BasicEvent mvbe = new BasicEvent(msgHandler.getValues());
             * msg = handler.defEvent(mvbe);
             * break;
             */
            case TEMPLATESENDJOBFINISH:
            	event = new SentTmlJobEvent(body.getValues());
                break;
            case MASSSENDJOBFINISH:
            	event = new SentAllJobEvent(body.getValues());
                break;
            case kf_create_session:
            	event = new CustomServiceEvent(body.getValues());
                break;
            case kf_close_session:
            	event = new CustomServiceEvent(body.getValues());
                break;
            case kf_switch_session:
            	event = new CustomServiceEvent(body.getValues());
                break;
            default:
            	event = new BasicEvent(body.getValues());
                break;
        }
        return handler.handler(event);
	}
	
}
