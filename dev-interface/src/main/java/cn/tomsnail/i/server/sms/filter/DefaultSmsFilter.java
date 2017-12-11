package cn.tomsnail.i.server.sms.filter;

import cn.tomsnail.i.core.DataContentPckt;

/**
 *        
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午3:11:08
 * @see 
 */
public class DefaultSmsFilter implements ISmslFilter{

	@Override
	public boolean filter(DataContentPckt dataContentPckt) {
		return true;
	}

}
