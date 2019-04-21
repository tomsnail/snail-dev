package cn.tomsnail.snail.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *        默认日志服务，记录到logger
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:44:22
 * @see 
 */
@Component("fileLogService")
public class DefaultLogService implements LogService{
	
	private  static  final Logger logger = LoggerFactory.getLogger("DefaultLogService"); 

	@Override
	public void log(Log log) {
		if(log==null||log.getContent()==null){
			return;
		}
		if(log.getLevel()==Log.ERROR){
			logger.error(log.toString());
		}else{
			logger.info(log.toString());
		}		
	}

}
