package com.squidtopusstudios.zerobitengine.core;

import com.squidtopusstudios.zerobitengine.ZeroBitGame;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 *  Static class for managing global resources and settings
 */
public class ZeroBit {

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

    private static ZeroBitGame gameInstance;

    public static void setGame(ZeroBitGame game) {
        if (gameInstance == null) {
            gameInstance = game;
        } else {
            Logger.getInstance().logError("Game instance already set, you should only be using 1!", new IllegalStateException());
        }
        if (!gameInstance.isInitialised()) {
            Logger.getInstance().logError("Game instance hasn't been initialised, " +
                    "make sure to call super.create() in your ZeroBitGame class", new IllegalStateException());
        }
    }

    public static void exit() {
        Logger.getInstance().logInfo("Exiting Game");
        gameInstance.dispose();
    }
}
