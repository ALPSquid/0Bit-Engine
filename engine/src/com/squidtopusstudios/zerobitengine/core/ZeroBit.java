package com.squidtopusstudios.zerobitengine.core;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.squidtopusstudios.zerobitengine.World;
import com.squidtopusstudios.zerobitengine.ZeroBitGame;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 *  Static class for managing global resources and settings
 */
public class ZeroBit {

    public static final String VERSION = "0.0.1";
    public static boolean DEBUG = false;
    public static int width = 0;
    public static int height = 0;
    public static Color bg_colour = new Color(0,0,0,1);
    public static float scale = 1f;

    public static class ResourceType {
        public static final Class<?> TEXTURE = com.badlogic.gdx.graphics.Texture.class;
        public static final Class<?> PIXMAP = com.badlogic.gdx.graphics.Pixmap.class;
        //public static final Class<?> FREE_TYPE_FONT = com.badlogic.gdx.graphics.g2d.freetype.FreeType.class;
        public static final Class<?> BITMAP_FONT = com.badlogic.gdx.graphics.g2d.BitmapFont.class;
        public static final Class<?> PARTICLE_EFFECT = com.badlogic.gdx.graphics.g2d.ParticleEffect.class;
        public static final Class<?> MUSIC = com.badlogic.gdx.audio.Music.class;
        public static final Class<?> SOUND = com.badlogic.gdx.audio.Sound.class;
        public static final Class<?> TEXTURE_ATLAS = com.badlogic.gdx.graphics.g2d.TextureAtlas.class;
        public static final Class<?> SKINS = com.badlogic.gdx.scenes.scene2d.ui.Skin.class;
        public static final Class<?> I18N_BUNDLE = com.badlogic.gdx.utils.I18NBundle.class;

    }

    /**
     * Gdx log types just to keep all logging functionality in one place
     */
    public static int LOG_NONE = Application.LOG_NONE;
    public static int LOG_DEBUG = Application.LOG_DEBUG;
    public static int LOG_ERROR = Application.LOG_ERROR;
    public static int LOG_INFO = Application.LOG_INFO;

    public static Logger logger = Logger.getInstance();
    public static Managers managers;
    private static ZeroBitGame gameInstance;
    private static World worldInstance;

    public synchronized static void setGame(ZeroBitGame game) {
        if (gameInstance == null) {
            gameInstance = game;
        } else {
            ZeroBit.logger.logError("Game instance already set, you should only be using 1!", new IllegalStateException());
        }
        if (!gameInstance.isInitialised()) {
            ZeroBit.logger.logError("Game instance hasn't been initialised, " +
                    "make sure to call super.create() in your ZeroBitGame class", new IllegalStateException());
        }
    }

    public synchronized static void initManagers() {
        managers = Managers.getInstance();
    }

    public synchronized static void exit() {
        ZeroBit.logger.logDebug("Exiting Game");
        gameInstance.dispose();
    }

    /**
     * Gets the main {@link ZeroBitGame}
     * @return the active {@link ZeroBitGame}
     */
    public static ZeroBitGame getGame() {
        return gameInstance;
    }

    public static boolean worldSet() {
        return worldInstance != null;
    }

    public static void setWorld(World world) {
        worldInstance = world;
    }

    /**
     * Gets the active {@link World}
     * @return the active {@link World}
     */
    public static World getWorld() {
        return worldInstance;
    }

    /**
     * Wrapper for Gdx.app.setLogLevel simply to keep all logging functionality in one place
     * @param logLevel Logging level (int) to set:
     *                           LOG_NONE: mutes all logging.
     *                           LOG_DEBUG: logs all messages.
     *                           LOG_ERROR: logs only error messages.
     *                           LOG_INFO: logs error and normal messages.
     *
     */
    public static void setLogLevel(int logLevel) {
        Gdx.app.setLogLevel(logLevel);
    }

    /**
     * Toggles the debug renderer and overlays.
     * @param debug on = true
     */
    public static void setDebug(boolean debug) {
        DEBUG = debug;
    }

    /**
     * Set the default GL clear colour (background colour)
     * Note: Before anyone complains,  I've had to use 'color' for years so I'm going
     * to damn well use the correct spelling in my own engine ;)
     * @param r red value (0-255)
     * @param g green value (0-255)
     * @param b blue value (0-255)
     * @param a alpha value (0-1)
     */
    public static void setColour(int r, int g, int b, float a) {
        bg_colour = new Color(r/255f, g/255f, b/255f, a);
    }
}
