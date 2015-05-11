package com.squidtopusstudios.zerobit.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.squidtopusstudios.zerobit.ZBGame;

import java.util.ArrayList;
import java.util.List;


/**
 * Super class for creating simple splash screens (fading logos).
 * Usage:
 *      In the load method:
 *      1. Optional: Set the 'targetScreen' variable to the ID of the screen you want to load after this screen. Default is main_menu
 *      2. Optional: Set the time in seconds ('splashTime') that each logo should be displayed for. Default is 2 seconds
 *      3. Create your logo sprites and add them to the 'splashes' list.
 *      4. Call super.load()
 */
public class SplashScreen extends ZBScreen {

	private SpriteBatch batch;
	private float timer;
	/** Current splash being displayed, corresponds to splashes index */
	private int currentSplash = 0;
	/** Current alpha of the current splash */
	private float splashAlpha = 0;
	/** Total splashes to display, starting from 0 */
	private int totalSplashes;


	/** List of splash textures in display order. Add your splashes to this list */
	protected List<Sprite> splashes = new ArrayList<>();
	/** Time to display each logo for in seconds */
	protected float splashTime = 2;
	/** Screen name to load after splashes have completed */
	protected String targetScreen = Screens.MAIN_MENU;


	public SplashScreen(ZBGame game) {
        super(game);
    }

	@Override
	public void load() {
		batch = new SpriteBatch();
		totalSplashes = splashes.size() - 1;

		// Center splashes
		for (Sprite splash : splashes) {
			splash.setPosition(Gdx.graphics.getWidth() / 2 - splash.getWidth() / 2, Gdx.graphics.getHeight() / 2 - splash.getHeight() / 2);
			splash.setAlpha(0);
			splash.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		}

		// Load the target screen while splashes are being shown
		game.getScreens().getScreen(targetScreen).load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		//Gdx.gl.glClearColor(0, 0, 0, 0);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		timer += delta;
		if (timer >= splashTime) {
			if (splashAlpha <= 0) {
				splashAlpha = 0;
				currentSplash++;
				if (currentSplash > totalSplashes) {
					game.getScreens().setScreen(targetScreen, false);
				} else {
					timer = 0;
				}
			}
			// Fade splash out
			splashAlpha -= delta;
		} else {
			// Fade splash in
			splashAlpha += delta;
		}
		if (splashAlpha > 1) splashAlpha = 1;
		else if (splashAlpha < 0) splashAlpha = 0;
		if (currentSplash <= totalSplashes) splashes.get(currentSplash).setAlpha(splashAlpha);

		batch.begin();
		for (Sprite splash : splashes) splash.draw(batch);
		batch.end();

		// Allow skipping of splashes
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			//game.getScreens().setScreen(targetScreen, false);
			timer = splashTime;
			splashAlpha = 0;
			splashes.get(currentSplash).setAlpha(splashAlpha);
		}
	}

	@Override
	public void dispose() {
		if (batch != null) batch.dispose();
	}
}
