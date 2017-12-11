package cn.tomsnail.i.server.sms.receive;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.tomsnail.i.core.DataContentPckt;
import cn.tomsnail.i.server.email.handler.IEmailHandler;
import cn.tomsnail.jms.IJmsReceiveCall;
import cn.tomsnail.jms.Message;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午3:02:10
 * @see 
 */
public class SmsReciever implements IJmsReceiveCall{
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(SmsReciever.class);
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Resource
	protected IEmailHandler handler;
	
	private void handler(Message message){
		try {
			String dp = (String) message.getMessage();
			DataContentPckt dataContentPckt = OBJECT_MAPPER.readValue(dp, DataContentPckt.class);
			handler.handler(dataContentPckt);
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}
	
	@Override
	public void call(Message msg) {
		this.handler(msg);
	}
	
}
