package com.squidtopusstudios.zerobitengine.refact.util;

import com.badlogic.gdx.Gdx;

/**
 * Console Logger using Gdx.app.logtype
 */
public class ConsoleLogger extends Logger {


    public ConsoleLogger(Logger nextLogger) {
        super(nextLogger);
    }
    public ConsoleLogger() {
        super();
    }

    @Override
    protected void write(String tag, String message, Throwable exception, Logger.LogType type) {
        switch (type) {
            case INFO:
                Gdx.app.log(tag, message);
                break;
            case ERROR:
                if (exception != null) {
                    Gdx.app.error(tag, message, exception);
                } else {
                    Gdx.app.error(tag, message);
                }
                break;
            case DEBUG:
                if (exception != null) {
                    Gdx.app.debug(tag, message, exception);
                } else {
                    Gdx.app.debug(tag, message);
                }
                break;
        }
    }
}
