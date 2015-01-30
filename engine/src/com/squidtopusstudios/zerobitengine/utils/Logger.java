package com.squidtopusstudios.zerobitengine.utils;

import com.badlogic.gdx.Gdx;
import com.squidtopusstudios.zerobitengine.ZeroBit;

/**
 * Singleton - LibGDX Logging Wrapper providing extended functionality like caller class, method and line number
 */
public class Logger {

    private enum LogType {
        INFO, ERROR, DEBUG
    }

    /**
     * Simple class to allow easy access to a single caller class, method and line number at one time
     * LOG_NONE: mutes all logging.
     * LOG_DEBUG: logs all messages.
     * LOG_ERROR: logs only error messages.
     * LOG_INFO: logs error and normal messages.
     */
    private class CallerDetails {
        public String className;
        public String methodName;
        public String lineNumber;

        public void clear() {
            className = "";
            methodName = "";
            lineNumber = "";
        }
    }
    private CallerDetails callerDetails = new CallerDetails();
    private static Logger loggerInstance;


    private Logger() {}

    /**
     * Creates a new Logger instance if one doesn't exist and returns it
     * @return Logger instance
     */
    public static Logger getInstance() {
        if (loggerInstance == null) {
            loggerInstance = new Logger();
        }
        return loggerInstance;
    }

    /**
     * Wrapper for GDX.app.error logging
     * @param message Message to log
     * @param exception Exception to log
     */
    public void logError(String message, Throwable exception) {
        log(message, exception, LogType.ERROR);
    }

    /**
     * Allows the use of logError without providing an exception
     * @param message Message to log
     */
    public void logError(String message) {
        logError(message, null);
    }

    /**
     * Wrapper for Gdx.app.log logging
     * @param message Message to log
     */
    public void logInfo(String message) {
        log(message, null, LogType.INFO);
    }

    /**
     * Wrapper for Gdx.app.debug logging
     * @param message Message to log
     * @param exception Exception to log
     */
    public void logDebug(String message, Throwable exception) {
        log(message, exception, LogType.DEBUG);
    }
    /**
     * Allows the use of logDebug without providing an exception
     * @param message Message to log
     */
    public void logDebug(String message) {
        logDebug(message, null);
    }

    /**
     * Single wrapper function for the different Gdx.app.log types. Calls getCallerDetails() to get the caller
     * class name, method name and line number
     * @param message Message to log
     * @param exception Exception to log
     * @param type Type of logging to perform: LogType.INFO, LogType.ERROR, LogType.DEBUG
     */
    private void log(String message, Throwable exception, LogType type) {
        getCallerDetails();
        String tag = callerDetails.className + "(" + callerDetails.methodName + ", ln "
                + callerDetails.lineNumber + ")";
        switch (type) {
            case INFO:
                Gdx.app.log(tag, message);
                break;
            case ERROR:
                if (exception == null) {
                    Gdx.app.error(tag, message);
                } else {
                    Gdx.app.error(tag, message, exception);
                    ZeroBit.exit();
                }
                break;
            case DEBUG:
                if (exception == null) {
                    Gdx.app.debug(tag, message);
                } else {
                    Gdx.app.debug(tag, message, exception);
                }
                break;
        }
    }

    /**
     * Gets the class name, method name and line number of the external caller of the logging function.
     * These are stored in the callerDetails instance
     */
    private void getCallerDetails() {
        //StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String className = "";
        String methodName = "";
        int lineNumber = 0;
        for (int i=4; i < stackTraceElements.length; i++) {
            if (!stackTraceElements[i].getClassName().contains("Logger")) {
                className = stackTraceElements[i].getClassName();
                methodName = stackTraceElements[i].getMethodName();
                lineNumber = stackTraceElements[i].getLineNumber();
                break;
            }
        }
        callerDetails.clear();
        callerDetails.className = className;
        callerDetails.methodName = methodName;
        callerDetails.lineNumber = String.valueOf(lineNumber);
    }
}
