package com.squidtopusstudios.zerobit.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages a libGDX AssetManager. Load and request assets through this class.<br/>
 * Use Assets.get(path, type) to load an asset or retrieve it if it exists.
 * See https://github.com/libgdx/libgdx/wiki/Managing-your-assets for more info
 */
public class AssetUtils {

    public static Map<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
    public static Skin skinDefault = new Skin();

    /**
     * Gets a font from the cache if it exists otherwise creates it based on the following arguments
     * @param fontID id of the font, must be in the format: {ttf-file-name}-{size}
     * @param defaultPath path to the ttf file used to create the font if it doesn't exist
     * @return the BitmapFont or null if it doesn't exist and no path is provided
     */
    public static BitmapFont getFont(String fontID, String defaultPath) {
        if (fonts.containsKey(fontID)) {
            return fonts.get(fontID);
        }
        else if (defaultPath != null) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(defaultPath));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = Integer.parseInt(fontID.split("-")[1]);
            BitmapFont font = generator.generateFont(parameter);
            generator.dispose();
            fonts.put(fontID, font);
            return font;
        }
        return null;
    }

    /**
     * see {@link #getFont}
     */
    public static BitmapFont getFont(String fontID) {
        return getFont(fontID, null);
    }

    /**
     * Creates an animation. Loops by default
     * @param frameDuration frame duration in seconds
     * @param atlas TextureAtlas to use for the Animation
     * @param frames frame numbers to use in the Animation
     * @return the created Animation
     */
    public static Animation createAnimation(float frameDuration, TextureAtlas atlas, Integer[] frames) {
        Array<TextureAtlas.AtlasRegion> regions = new Array<TextureAtlas.AtlasRegion>();
        for (int frame : frames) {
            regions.add(atlas.getRegions().get(frame));
        }
        return new Animation(frameDuration, regions, Animation.PlayMode.LOOP);
    }

    /**
     * Creates an animation. Loops by default
     * @param frameDuration frame duration in seconds
     * @param atlas TextureAtlas to use for the Animation
     * @param startFrame start frame number
     * @param endFrame start frame number
     * @return the created Animation
     */
    public static Animation createAnimation(float frameDuration, TextureAtlas atlas, int startFrame, int endFrame) {
        Array<Integer> frames = new Array<Integer>();
        for (int i = startFrame; i <= endFrame; i++) {
            frames.add(i);
        }
        return createAnimation(frameDuration, atlas, (Integer[])frames.toArray(Integer.class));
    }
}
