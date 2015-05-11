package com.squidtopusstudios.zerobit.util.logging;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

/**
 * Abstract Logger class providing extended functionality like caller class, method and line number
 */
public abstract class Logger {

    public static int LOG_NONE = Application.LOG_NONE;
    public static int LOG_DEBUG = Application.LOG_DEBUG;
    public static int LOG_ERROR = Application.LOG_ERROR;
    public static int LOG_INFO = Application.LOG_INFO;

    /**
     * LOG_NONE: mutes all logging.
     * LOG_DEBUG: logs all messages.
     * LOG_ERROR: logs only error messages.
     * LOG_INFO: logs error and normal messages.
     */
    protected enum LogType {
        INFO, ERROR, DEBUG
    }

    /**
     * Simple class to allow easy access to a single caller class, method and line number at one time
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
    protected Logger nextLogger;


    /**
     * @param nextLogger Next logger instance to call, null if there isn't one
     */
    public Logger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public Logger() {
        this(null);
    }

    /**
     * Wrapper for GDX.app.error logging
     * @param message Message to log
     * @param exception Exception to log
     */
    public void logError(Object message, Throwable exception) {
        log(message, exception, LogType.ERROR);
    }

    /**
     * Allows the use of logError without providing an exception
     * @param message Message to log
     */
    public void logError(Object message) {
        logError(message, null);
    }

    /**
     * Wrapper for Gdx.app.log logging
     * @param message Message to log
     */
    public void logInfo(Object message) {
        log(message, null, LogType.INFO);
    }

    /**
     * Wrapper for Gdx.app.debug logging
     * @param message Message to log
     * @param exception Exception to log
     */
    public void logDebug(Object message, Throwable exception) {
        log(message, exception, LogType.DEBUG);
    }
    /**
     * Allows the use of logDebug without providing an exception
     * @param message Message to log
     */
    public void logDebug(Object message) {
        logDebug(message, null);
    }

    /**
     * Single wrapper function for the different Gdx.app.log types. Calls getCallerDetails() to get the caller
     * class name, method name and line number
     * @param message Message to log
     * @param exception Exception to log
     * @param type Type of logging to perform: LogType.INFO, LogType.ERROR, LogType.DEBUG
     */
    private void log(Object message, Throwable exception, LogType type) {
        if (Gdx.app.getLogLevel() != LOG_NONE) {
            getCallerDetails();
            String tag = callerDetails.className + "(" + callerDetails.methodName + ", ln "
                    + callerDetails.lineNumber + ")";
            write(tag, String.valueOf(message), exception, type);
            if (nextLogger != null) nextLogger.write(tag, String.valueOf(message), exception, type);

        }
    }

    protected abstract void write(String tag, String message, Throwable exception, LogType type);

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

    /**
     * Wrapper for Gdx.app.setLogLevel simply encapsulate all logging functionality
     * While this is more logical as a static method, accessing everything under one roof (Zbe) is easier to use
     * @param logLevel Logging level (int) to set:
     *                           Logger.LOG_NONE: mutes all logging.
     *                           Logger.LOG_DEBUG: logs all messages.
     *                           Logger.LOG_ERROR: logs only error messages.
     *                           Logger.LOG_INFO: logs error and normal messages.
     */
    public void setLogLevel(int logLevel) {
        Gdx.app.setLogLevel(logLevel);
    }
}
