package com.squidtopusstudios.zerobit.screens;

import com.badlogic.gdx.physics.box2d.Box2D;
import com.squidtopusstudios.zerobit.ZBGame;
import com.squidtopusstudios.zerobit.entity.systems.InputSystem;
import com.squidtopusstudios.zerobit.ui.views.DebugUIView;
import com.squidtopusstudios.zerobit.ui.controllers.DebugController;
import com.squidtopusstudios.zerobit.input.InputMapper;
import com.squidtopusstudios.zerobit.util.observers.Observer;
import com.squidtopusstudios.zerobit.worlds.ZBWorld;

import java.util.HashMap;
import java.util.Map;

/**
 * Main Game screen
 */
public abstract class GameScreen extends ZBScreen implements Observer {

    protected InputMapper inputMapper = new InputMapper();
    protected Map<String, ZBWorld> worlds = new HashMap<String, ZBWorld>();
    protected ZBWorld currentWorld;


    public GameScreen(ZBGame game) {
        super(game);
        Box2D.init();
    }

    @Override
    public void load() {
        addController("debug", new DebugController(this));
        // Adding the view is optional; you can just instantiate the view inline instead
        addView("debug", new DebugUIView((DebugController) getController("debug")));
        // Set the view for the controller. Sometimes you want to wait until loading has completed to set a view which is why it isn't done automatically
        getController("debug").setView(getView("debug"));

    }

    @Override
    public void loadComplete() {
        super.loadComplete();
        getInputMultiplexer().addProcessor(inputMapper);
    }

    @Override
    public void render(float delta) {
        if (currentWorld != null && !isPaused()) currentWorld.update(delta);
        super.render(delta);
    }

    @Override
    public void pause() {
        super.pause();
        if (currentWorld != null) currentWorld.getEntities().pauseSystems(true);
    }

    @Override
    public void resume() {
        super.resume();
        if (currentWorld != null) currentWorld.getEntities().pauseSystems(false);
    }

    @Override
    public void dispose() {
        for (ZBWorld world : worlds.values()) {
            world.dispose();
        }
    }

    /**
     * Changes the world to the registered world identified by name. The world will be loaded if it hasn't been already
     * @param name name/ID the world was registered as
     */
    public void setWorld(String name) {
        if (game.getScreens().getCurrentScreenID().equals(Screens.GAME)) {
            game.getScreens().setScreen(Screens.LOADING, false);
            ((LoadingScreen)game.getScreens().getScreen()).setTarget(Screens.GAME);
        }
        // Clean up observers if switching from a world
        if (currentWorld != null) {
            currentWorld.getEntities().pauseSystems(true);
            resetViews();
        }
        currentWorld = worlds.get(name);
        if (currentWorld.isLoaded()) {
            currentWorld.reset();
            currentWorld.getEntities().pauseSystems(false);
        }
        currentWorld.doLoad(new Runnable() {
            @Override
            public void run() {
                if (isLoaded()) {
                    // Example
                }
            }
        });

    }

    /**
     * Adds a world to the list of worlds for retrieval later
     * @param name name/ID to identify the world
     * @param world {@link ZBWorld} instance
     */
    public void addWorld(String name, ZBWorld world) {
        worlds.put(name, world);
    }

    public ZBWorld getWorld() {
        return currentWorld;
    }

    public Map<String, ZBWorld> getWorlds() {
        return worlds;
    }

    public InputMapper getInputMapper() {
        return inputMapper;
    }
}
