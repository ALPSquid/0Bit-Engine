package com.squidtopusstudios.zerobit.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.squidtopusstudios.zerobit.ZBGame;
import com.squidtopusstudios.zerobit.ui.views.UIView;
import com.squidtopusstudios.zerobit.ui.controllers.UIController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Abstract screen implementation for Western Knights screens
 */
public abstract class ZBScreen implements Screen {

    /** Enum like class for screen identifiers.
     * Loading and Game are the only identifiers used by the engine, so when using your own, be sure to use the same identifier
     */
    public static class Screens {
        public static final String LOADING = "loading";
        public static final String SPLASH = "splash";
        public static final String MAIN_MENU = "main_menu";
        public static final String GAME = "game";
        public static final String SCORE = "score";
        public static final String GAME_OVER = "game_over";
        public static final String CREDITS = "credits";
        public static final String END = "end";
    }

    protected ZBGame game;
    /** All input systems should be added to this multiplexer */
    protected InputMultiplexer inputMultiplexer;
    /** Map of UIControllers. Any controllers added to this map will be updated automatically */
    private Map<String, UIController> uiControllers = new LinkedHashMap<String, UIController>();
    /** Map of UIViews. This is a convenience map for accessing your views, the engine interacts with views through its controller */
    private Map<String, UIView> uiViews = new LinkedHashMap<String, UIView>();
    /** Whether the screen has been loaded */
    private boolean loaded = false;
    /** Whether the screen is paused */
    private boolean paused = false;


    public ZBScreen(ZBGame game) {
        this.game = game;
        inputMultiplexer = new InputMultiplexer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /** Request assets to load and perform any loading logic */
    public abstract void load();

    /**
     * Called when loading has finished
     */
    public void loadComplete() {
        loaded = true;
    }

    /**
     * Updates added controllers
     */
    @Override
    public void render(float delta) {
        for (UIController controller : uiControllers.values()) {
            controller.update(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        for (UIController controller : uiControllers.values()) {
            controller.resize(width, height);
        }
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (UIController controller : uiControllers.values()) {
            if (controller != null) controller.dispose();
        }
        uiControllers.clear();
        loaded = false;
    }

    /**
     * Adds a controller to the screen which will be updated automatically
     * @param ID ID for the controller
     * @param controller the UIController instance to add
     */
    public void addController(String ID, UIController controller) {
        uiControllers.put(ID, controller);
    }
    /**
     * @param ID ID of the added controller
     * @return the requested controller
     */
    public UIController getController(String ID) {
        return uiControllers.get(ID);
    }

    /**
     * Adds a view to the screen for easy view management. Unlike controllers, this is optional
     * @param ID ID for the controller
     * @param view the UIView instance to add
     */
    public void addView(String ID, UIView view) {
        uiViews.put(ID, view);
    }
    /**
     * @param ID ID of the added view
     * @return the requested view
     */
    public UIView getView(String ID) {
        return uiViews.get(ID);
    }

    /**
     * Calls resetView on all added controllers
     */
    public void resetViews() {
        for (UIController controller : uiControllers.values()) {
            controller.resetView();
        }
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setScreen(String screen) {
        getGame().getScreens().setScreen(screen);
    }

    public ZBGame getGame() { return game; }

    /**
     * Gets the InputMultiplexer for this Screen. You should add any InputProcessors to this.
     * @return this Screen's InputMultiplexer instance
     */
    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }
}
