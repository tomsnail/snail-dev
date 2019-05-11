package cn.tomsnail.snail.core.gen.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.class);

    public static void info(String format, Object... arguments){
        logger.info(format,arguments);
    }

    public static void errror(String msg,Throwable t){
        logger.error(msg,t);
    }
}
