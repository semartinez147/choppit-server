package com.semartinez.choppit.service;

import java.util.logging.Logger;

public class Log extends Logger {

  protected Log(String name, String resourceBundleName) {
    super(name, resourceBundleName);
  }

  public static void info(String loggerName, String msg) {
    Logger logger = Logger.getLogger(loggerName);
    logger.info(msg);
  }

  public static void warn(String loggerName, String msg) {
    Logger logger = Logger.getLogger(loggerName);
    logger.warning(msg);
  }

  public static void severe(String loggerName, String msg) {
    Logger logger = Logger.getLogger(loggerName);
    logger.severe(msg);
  }

}
