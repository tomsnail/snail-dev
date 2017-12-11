package cn.tomsnail.i.server.sms.handler;

import cn.tomsnail.i.core.DataContentPckt;

/**
 *        sms处理
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午2:59:54
 * @see 
 */
public interface ISmsHandler {

	public void handler(DataContentPckt dataContentPckt);
	
}
