package cn.tomsnail.log;

import java.nio.charset.Charset;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.layout.PatternLayout;

import cn.tomsnail.log.ls.FileAppender;
import cn.tomsnail.log.ls.FileLogFactory;
import cn.tomsnail.log.ls.LogSystem;

public class LogSystemTest {

	private static final Logger logger = LogManager.getLogger(LogSystemTest.class);
	
	private static final LogSystem buyLog = FileLogFactory.instance().create("buy");
	private static final LogSystem traLog = FileLogFactory.instance().create("tra");

	public static void main(String[] args) {
//		logger.info(System.currentTimeMillis() + "");
//		System.out.println(123);
//
//		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
//		final org.apache.logging.log4j.core.config.Configuration config = ctx.getConfiguration();
//		PatternLayout layout = PatternLayout.createLayout("%d{yyyy-MM-dd HH:mm:ss} [%t] (%F:%L)  - %m%n", null, config,
//				null, Charset.forName("UTF-8"), true, false, null, null);
//		Appender appender = FileAppender.createAppender("File", null, layout, false);
//		Appender appender1 = FileAppender.createAppender("File1", null, layout, false);
//		appender.start();
//		appender1.start();
//		config.addAppender(appender);
//		config.addAppender(appender1);
//
//		config.getLoggerConfig("ROOT").addAppender(appender, Level.ERROR, null);
//		config.getLoggerConfig("ROOT").addAppender(appender1, Level.ERROR, null);
//		ctx.updateLoggers(config);
//		
//		logger.error(System.currentTimeMillis() + ">>>>");
		
		buyLog.log("12314");
		traLog.log("test:{}", "43134");
		logger.info("123");
		
		
	}

}
