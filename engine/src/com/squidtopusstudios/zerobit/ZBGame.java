package com.squidtopusstudios.zerobit;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.squidtopusstudios.zerobit.screens.ScreenManager;
import com.squidtopusstudios.zerobit.util.logging.Logger;

/**
 * Game entry point
 */
public class ZBGame extends Game {

	private ScreenManager screenManager;


	@Override
	public void create () {
		ZeroBit.logger.setLogLevel(Logger.LOG_DEBUG);
		ZeroBit.logger.logDebug("Starting game");

		screenManager = new ScreenManager(this);
	}

	@Override
	public void render() {
		super.render();
		ZeroBit.assetManager.update();
	}

	@Override
	public void dispose() {
		screenManager.dispose();
		ZeroBit.assetManager.dispose();
	}

	/**
	 * Closes the game
	 */
	public void exit() {
		Gdx.app.exit();
	}

	public ScreenManager getScreens() {
		return screenManager;
	}
}