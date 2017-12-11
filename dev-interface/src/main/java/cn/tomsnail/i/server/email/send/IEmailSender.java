package cn.tomsnail.i.server.email.send;

import cn.tomsnail.i.core.DataContentPckt;

/**
 *        email发送
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午3:12:45
 * @see 
 */
public interface IEmailSender {

	public boolean send(DataContentPckt dataContentPckt);
	
}
