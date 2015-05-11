package com.squidtopusstudios.zerobit.screens;


import com.squidtopusstudios.zerobit.ZBGame;
import com.squidtopusstudios.zerobit.ZeroBit;

/**
 * Simple Loading Screen. To create your own loading screen, extend this class and set an instance of it as "loading" in the ScreenManager TODO: Docs
 */
public class LoadingScreen extends ZBScreen {

    private String targetScreen;


    public LoadingScreen(ZBGame game) {
        super(game);
        load();
    }

    @Override
    public void load() {
        /*uiControllers.put("loading", new LoadingController(this));
        uiViews.put("loading", new Loading((LoadingController)uiControllers.get("loading")));
        uiControllers.get("loading").setView(uiViews.get("loading"));*/
    }

    @Override
    public void loadComplete() {
        super.loadComplete();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (ZeroBit.assetManager.update()) {
            if (targetScreen == null) ZeroBit.logger.logDebug("Target screen is null!");
            else {
                game.getScreens().setScreen(targetScreen, false);
            }
        }
    }

    /** Set the target screen to load */
    public LoadingScreen setTarget(String screen) {
        targetScreen = screen;
        return this;
    }
}
