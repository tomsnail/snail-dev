package cn.tomsnail.snail.core.log;

import org.springframework.stereotype.Component;

/**
 *        系统日志记录
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:44:38
 * @see 
 */
@Component("sysoLogService")
public class DefaultSystemOutLogService implements LogService{

	@Override
	public void log(Log log) {
		if(log==null||log.getContent()==null){
			return;
		}
	}

}
