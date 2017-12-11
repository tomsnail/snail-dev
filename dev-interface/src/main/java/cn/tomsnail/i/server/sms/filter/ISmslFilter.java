package cn.tomsnail.i.server.sms.filter;

import cn.tomsnail.i.core.DataContentPckt;

/**
 *        sms过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午4:51:07
 * @see 
 */
public interface ISmslFilter {

	public boolean filter(DataContentPckt dataContentPckt);
	
}
