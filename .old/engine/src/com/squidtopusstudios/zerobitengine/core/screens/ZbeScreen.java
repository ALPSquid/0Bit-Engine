package com.squidtopusstudios.zerobitengine.core.screens;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.squidtopusstudios.zerobitengine.World;
import com.squidtopusstudios.zerobitengine.WorldBase;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;

/**
 * Base 0Bit Screen. Extend this in your own screens
 */
public class ZbeScreen implements Screen {
    public InputMultiplexer input;
    protected WorldBase world;

    /**
     * Called on screen registration. Create any objects here
     */
    public void create() {
    }

    public boolean hasWorld() {
        return world != null;
    }

    /**
     * Sets the current {@link com.squidtopusstudios.zerobitengine.World} instance
     * @param world {@link com.squidtopusstudios.zerobitengine.World} instance to set
     */
    public void setWorld(WorldBase world) {
        ZeroBit.logger.logDebug("Setting world as "+world);
        this.world = world;
        ZeroBit.setWorld(this.world);
    }

    /**
     * Returns the current {@link World} instance or logs an error if one hasn't been set
     * @return Current {@link World} instance
     */
    public WorldBase getWorld() {
        if (world == null) {
            ZeroBit.logger.logError("World instance has not been set! Use ZbeScreen.setWorld(world);");
            return null;
        }
        return world;
    }

    @Override
    public void render(float deltaTime) {
        if (hasWorld()) {
            world.update();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    /**
     * Called on when screen is set. Reset objects here
     */
    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
        if (hasWorld()) world.dispose();
    }
}