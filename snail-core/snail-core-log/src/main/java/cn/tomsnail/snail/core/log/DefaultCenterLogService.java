package cn.tomsnail.snail.core.log;



import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.mom.kafka.KafkaAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cn.tomsnail.snail.core.config.client.ConfigClientFactory;
import cn.tomsnail.snail.core.util.configfile.ConfigHelp;
import cn.tomsnail.snail.core.util.string.StringUtils;






/**
 *        默认日志中心服务
 * @author yangsong
 * @version 0.0.1
 * @status 正常
 * @date 2016年9月21日 下午5:43:00
 * @see 
 */
@Component("centerLogService")
public class DefaultCenterLogService implements LogService{
	
	private static final String LOG_NAME  = ConfigHelp.getInstance("config").getLocalConfig("system.log.center.LOG_NAME", "DefaultCenterLogService");
	
	private static final String LAYOUT = ConfigHelp.getInstance("config").getLocalConfig("system.log.center.LAYOUT", "%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n");
	
	
	@Autowired(required=false) 
	@Qualifier("CenterLogAddress")
	private  static String address = ConfigHelp.getInstance("config").getLocalConfig("system.log.center.address", "127.0.0.1:8457");
	
	public DefaultCenterLogService(){
		
	}
	
	@Override
	public void log(Log log) {
		
		String isDo = ConfigClientFactory.getInstance().getConfigClient().getConfig("system.log.center", "true");
		
		if(StringUtils.isBlank(isDo)||!isDo.equals("true")){
			return;
		}
		
		if(_LogInit.logger==null||log==null||log.getContent()==null){
			return;
		}

		if(log.getLevel()==Log.ERROR){
			_LogInit.logger.error(log.toString());
		}else{
			_LogInit.logger.info(log.toString());
		}		
	}
	
	/**
	 *        初始化日志记录器
	 * @methodauthor yangsong
	 * @methodversion 0.0.1
	 * @date 2016年9月21日 下午5:43:27
	 * @see 
	 * @param                   
	 * @return               
	 * @status 正常
	 * @exception no
	 */
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		DefaultCenterLogService.address = address;
	}
	//使用时加载
	private static class _LogInit{
		
		public static Logger logger = initLogger();
		
		private static Logger initLogger(){
		    try {
				final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
				final Configuration config = ctx.getConfiguration();
				Layout layout = PatternLayout.createLayout(LAYOUT, null, config, null, null, true, false, null, null);
				Property[] properties = new Property[1];
				String _address = ConfigClientFactory.getInstance().getConfigClient().getConfig("system.log.center.LogAddress", address);
				Property property =  Property.createProperty("bootstrap.servers",_address );
				properties[0] = property;
				Appender appender = KafkaAppender.createAppender(layout, null, ConfigHelp.getInstance("config").getLocalConfig("system.log.center.AppenderName", "kafka"), false, ConfigHelp.getInstance("config").getLocalConfig("system.log.center.topic", "LOG_CENTER"),properties,config);
				appender.start();
				config.addAppender(appender);
				AppenderRef ref = AppenderRef.createAppenderRef(LOG_NAME, null, null);
				AppenderRef[] refs = new AppenderRef[]{ref};
				LoggerConfig loggerConfig = LoggerConfig.createLogger("false", Level.ALL, LOG_NAME,"true", refs, null, config, null);
				loggerConfig.addAppender(appender, null, null);
				config.addLogger(LOG_NAME, loggerConfig);
				ctx.updateLoggers();
				return  ctx.getLogger(LOG_NAME);
			} catch (Exception e) {
				return null;
			}
		}
	}
	
}
