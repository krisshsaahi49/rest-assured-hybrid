package com.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

    private static volatile LogUtil logUtil;
    private Logger logger;
    private LogUtil(){
        logger = LogManager.getLogger();
    }

    public static LogUtil getInstance(){
        if(logUtil==null){
            synchronized (LogUtil.class){
                if(logUtil==null){
                    logUtil = new LogUtil();
                }
            }
        }
        return logUtil;
    }

    public void logAll(String message){
        logger.log(Level.ALL,message);
    }

    public void logError(String message){
        logger.log(Level.ERROR,message);
    }

    public void logDebug(String message){
        logger.log(Level.DEBUG,message);
    }

    public void logFatal(String message){
        logger.log(Level.FATAL,message);
    }

    public void logInfo(String message){
        logger.log(Level.INFO,message);
    }
}
