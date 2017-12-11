package cn.tomsnail.i.server.sms.send;

import cn.tomsnail.i.core.DataContentPckt;

/**
 *        sms实际发送接口
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午3:12:45
 * @see 
 */
public interface ISmsSender {

	public boolean send(DataContentPckt dataContentPckt);
	
}
