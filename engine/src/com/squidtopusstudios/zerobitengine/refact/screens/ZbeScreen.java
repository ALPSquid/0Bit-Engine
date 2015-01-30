package com.squidtopusstudios.zerobitengine.refact.screens;

import com.badlogic.gdx.Screen;
import com.squidtopusstudios.zerobitengine.refact.World;
import com.squidtopusstudios.zerobitengine.refact.Zbe;
import com.squidtopusstudios.zerobitengine.refact.ZbeGame;

/**
 * Base 0Bit Screen. Extend this in your own screens.
 */
public abstract class ZbeScreen implements Screen {

    protected ZbeGame game;
    protected World world;


    public ZbeScreen(ZbeGame game) {
        this.game = game;
    }

    /**
     * Called when this screen is set as the active game screen. Reset any objects here
     */
    @Override
    public void show() {
        if (hasWorld()) world.show();
    }

    @Override
    public void render(float delta) {
        if (hasWorld()) {
            world.update(delta);
        }
    }

    @Override
    public void dispose() {
        Zbe.logger.logDebug("Disposing");
        if (hasWorld()) world.dispose();
    }

    public boolean hasWorld() {
        return world != null;
    }

    /**
     * Sets the current {@link World} instance
     * @param world {@link World} instance to set
     */
    public void setWorld(World world) {
        Zbe.logger.logDebug("Setting world as " + world);
        this.world = world;
    }

    /**
     * Returns the current {@link World} instance or logs an error if one hasn't been set
     * @return Current {@link World} instance
     */
    public World getWorld() {
        if (world == null) {
            Zbe.logger.logError("World instance has not been set! Use ZbeScreen.setWorld(world);");
            return null;
        }
        return world;
    }
}
