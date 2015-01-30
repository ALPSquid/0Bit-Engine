package com.squidtopusstudios.zerobitengine.refact;

import com.squidtopusstudios.zerobitengine.refact.util.ConsoleLogger;
import com.squidtopusstudios.zerobitengine.refact.util.Logger;


/**
 * Provides global access to certain variables, enums and methods in the same way Gdx does
 */
public class Zbe {

    /**
     * Default logger implementation.
     * You can extend the functionality by writing your own class that extends Logger and setting it:
     *      Zbe.logger = new YourBetterLogger(Zbe.logger);
     */
    public static Logger logger = new ConsoleLogger();
}
