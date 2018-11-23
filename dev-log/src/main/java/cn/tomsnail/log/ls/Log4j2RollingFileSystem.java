package cn.tomsnail.log.ls;

import java.io.File;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.rolling.CompositeTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.DefaultRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.layout.PatternLayout;

import cn.tomsnail.util.string.StringUtils;

public final class Log4j2RollingFileSystem implements LogSystem{
	
	private static final AtomicInteger LEVEL = new AtomicInteger(320);

	
	private Logger logger;
	
	private Level level;
	
	private static final String DEFAULT_FORAMT = "[%d] [%t] (%F:%L) - %m%n";
	
	public Log4j2RollingFileSystem(String path,String fileName) {
		init(path,fileName,DEFAULT_FORAMT);
	}
	
	public Log4j2RollingFileSystem(String path,String fileName,String format) {
		init(path,fileName,format);
	}

	@Override
	public void log(String context) {
		logger.log(level, context);
	}

	@Override
	public void log(String message, Object...objects ) {
		logger.log(level, message,objects);
	}
	
	private void init(String path,String fileName,String format) {
		
		if(StringUtils.isBlank(path)) {
			path = "logs";
		}
		
		if(StringUtils.isBlank(fileName)) {
			throw new RuntimeException("fileName is null");
		}
		
		if(StringUtils.isBlank(format)) {
			format = DEFAULT_FORAMT;
		}
		
		Level level = Level.forName(fileName, LEVEL.incrementAndGet());
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = ctx.getConfiguration();
		PatternLayout layout = PatternLayout.createLayout(format, null, config,null, Charset.forName("UTF-8"), true, false, null, null);
		Appender appender = RollingRandomAccessFileAppender.createAppender(path+File.separator+fileName+".log", path+"/$${date:yyyy-MM}/"+fileName+"-%d{yyyy-MM-dd}-%i.log.gz", "true", fileName, null, null, CompositeTriggeringPolicy.createPolicy(TimeBasedTriggeringPolicy.createPolicy(null, null),SizeBasedTriggeringPolicy.createPolicy("30M")), DefaultRolloverStrategy.createStrategy("30", null, null, null, null, false, config), layout, null, null, null, null, config,String.valueOf(level.intLevel()));
		appender.start();
		config.addAppender(appender);
		config.getLoggerConfig("ROOT").addAppender(appender, level, null);
		ctx.updateLoggers(config);
		logger = LogManager.getLogger(fileName);
		this.level = level;
	}

	
}
