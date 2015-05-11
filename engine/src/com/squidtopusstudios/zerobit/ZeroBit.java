package com.squidtopusstudios.zerobit;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.squidtopusstudios.zerobit.data.SaveManager;
import com.squidtopusstudios.zerobit.util.logging.ConsoleLogger;
import com.squidtopusstudios.zerobit.util.logging.Logger;

/**
 * Global static references aren't good practice, but certain things need to be accessible from anywhere
 * TODO: Compile a final list of objects that absolutely must be in this class to clean it up
 */
public class ZeroBit {
    public static AssetManager assetManager = new AssetManager();
    public static Logger logger = new ConsoleLogger(); // TODO: Create file logger example
    public static SaveManager saveManager = new SaveManager(); //TODO: Is an instance of this necessary?
    /** Current debug toggle state. On = renderer and overlay active */
    public static boolean debugRender = false;
    /** Current gametime  */
    public static float gameTime = 1;

    /**
     * ---- Configuration Options ----
     */
    /** When using Box2D this is used to convert between pixels and meters */
    public static float metersToPixels = 60f;
    public static float pixelsToMeters = 1/metersToPixels;
    /** Key used to activate debug mode **/
    public static int debugKey = Input.Keys.APOSTROPHE;
    /** Whether debugging can be toggled */
    public static boolean debugEnabled = false;

    /** Day time alpha */
    public static float ambientAlphaDay = 0.8f;
    /** Night time alpha */
    public static float ambientAlphaNight = 0.1f;
}
