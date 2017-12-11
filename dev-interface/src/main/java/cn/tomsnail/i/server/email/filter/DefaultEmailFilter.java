package cn.tomsnail.i.server.email.filter;

import cn.tomsnail.i.core.DataContentPckt;

/**
 *        默认过滤
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年8月20日 下午3:11:08
 * @see 
 */
public class DefaultEmailFilter implements IEmailFilter{

	@Override
	public boolean filter(DataContentPckt dataContentPckt) {
		return true;
	}

}
