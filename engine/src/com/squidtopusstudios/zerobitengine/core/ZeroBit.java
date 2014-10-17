package com.squidtopusstudios.zerobitengine.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.squidtopusstudios.zerobitengine.World;
import com.squidtopusstudios.zerobitengine.ZeroBitGame;
import com.squidtopusstudios.zerobitengine.utils.Logger;
import com.squidtopusstudios.zerobitengine.utils.ZbeInputProcessor;

/**
 *  Static class for managing global resources and settings
 */
public class ZeroBit {

    public static final String ZBE_VERSION = "0.0.2";
    public static String appVersion = "0.0.1";
    private static boolean fixedTimeStep = false;
    public static boolean showDebugRenderer = false;
    public static boolean showDebugOverlay = false;
    public static boolean debug = false;
    public static int debugKey = 68; // tilde
    public static int targetWidth = Gdx.graphics.getWidth();
    public static int targetHeight = Gdx.graphics.getHeight();
    public static float scale = 1f;
    public static Color bg_colour = new Color(0,0,0,1);
    private static InputMultiplexer inputMultiplexer;

    /**
     * Supported resource types available when registering a resource
     */
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

    /** Physics Types. Use ZbeEntity.setPhysicsType(TYPE type). Default NONE
     *      NONE: No physics
     *      PLATFORMER: Applies world.gravity
     * **/
    public static enum PHYSICS_TYPE {
        NONE, PLATFORMER
    }

    /**
     * Built in entity states. Feel free to use extra ones.
     */
    public static class ENTITY_STATE {
        public static final String IDLE = "IDLE";
        public static final String MOVING = "MOVING";
        public static final String JUMPING = "JUMPING";
        public static final String FALLING = "FALLING";
        public static final String HIT = "HIT";
        public static final String DEAD = "DEAD";
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

    public synchronized static void setGame(String appVersion, ZeroBitGame game, int targetWidth, int targetHeight, boolean fixedTimeStep) {
        if (gameInstance == null) {
            gameInstance = game;
            ZeroBit.targetWidth = targetWidth;
            ZeroBit.targetHeight = targetHeight;
            ZeroBit.fixedTimeStep = fixedTimeStep;
            ZeroBit.appVersion = appVersion;
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
        managers.resourceManager().addResource("viewport_border", "assets/viewport_border.png", ResourceType.TEXTURE);
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

    public static boolean isWorldSet() {
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
     * Set the default GL clear colour (background colour).
     * You can use Utils.Colour.fromRGBA() to convert RGBA values into GL float values
     */
    public static void setBgColour(Color colour) {
        bg_colour = colour;
    }

    /**
     * Set the target game width and height
     * @param width target width
     * @param height target height
     */
    public static void setTargetDimensions(int width, int height) {
        ZeroBit.targetWidth = width;
        ZeroBit.targetHeight = height;
    }

    public static void toggleDebugOverlay() {
        if (debug) {
            showDebugOverlay = !showDebugOverlay;
        }
    }

    public static void toggleDebugRenderer() {
        if (debug) {
            showDebugRenderer = !showDebugRenderer;
        }
    }

    /**
     * Adds an entry to the debug overlay for monitoring custom objects. Unfortunately, due to the way java is purely
     * pass by value, if you want to monitor a changing value you'll need to call this in a method that runs every
     * tick like update() or render() (depending on the class).
     * For example, to monitor the player's x value, I'd call ZeroBit.debugValue("Player X", getX()) in the update()
     * method inside my ZbeEntity class: 'Player'.
     * @param name Name for the value, displayed 'name: value'
     * @param value value to display, displayed 'name: value'
     */
    public static void debugValue(String name, Object value) {
        managers.renderManager().getDebugOverlay().track(name, value);
    }

    /**
     * Set the InputMultiplexer for the game. Simply add and remove InputProcessor throughout the game accordingly
     * @param inputMultiplexer the InputMultiplexer instance to set
     */
    public static void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
        ZeroBit.inputMultiplexer = inputMultiplexer;
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Sets the world ZbeInputProcessor. Access with ZeroBit.input()
     * @param inputProcessor ZbeInputProcessor instance to add
     */
    public static void setGlobalInputProcessor(ZbeInputProcessor inputProcessor) {
        inputMultiplexer.addProcessor(0, inputProcessor);
    }
    /**
     * TODO: Adds an InputProcessor from a stage
     * @param inputProcessor InputProcessor instance to add
     */
    public static void addStageInputProcessor(InputProcessor inputProcessor) {
        inputMultiplexer.addProcessor(inputProcessor);
    }

    /**
     * Removes an InputProcessor from the inputMultiplexer
     * @param inputProcessor InputProcessor instance to add
     */
    public static void removeInputProcessor(InputProcessor inputProcessor) {
        inputMultiplexer.removeProcessor(inputProcessor);
    }

    /**
     * Get the global {@link ZbeInputProcessor} for the game. Use this to check for key presses on everything but stages
     * @return global registered {@link ZbeInputProcessor} for the game
     */
    public static ZbeInputProcessor input() {
        return (ZbeInputProcessor)inputMultiplexer.getProcessors().get(0);
    }

    public static boolean isFixedTimeStep() {
        return fixedTimeStep;
    }

    public static float getDelta() {
        return (Gdx.graphics.getDeltaTime() > 1f) ? 1f : Gdx.graphics.getDeltaTime();
    }
}
