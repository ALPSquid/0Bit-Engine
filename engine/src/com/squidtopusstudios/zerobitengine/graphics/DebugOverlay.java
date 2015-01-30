package com.squidtopusstudios.zerobitengine.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.squidtopusstudios.zerobitengine.ZeroBit;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Default Renderer, for more control you can either extend this class or create your own that implements IRenderer
 */
public class DebugOverlay implements Renderer {

    protected SpriteBatch batch;
    protected BitmapFont defaultFont;
    protected Map<String, String> debugValues;
    protected int padding;


    @Override
    public void create() {
        batch = new SpriteBatch();
        defaultFont = new BitmapFont();
        defaultFont.setColor(Color.WHITE);
        debugValues = new LinkedHashMap<String, String>();
        debugValues.put(Gdx.app.getApplicationListener().getClass().getSimpleName(), "v"+ZeroBit.appVersion);
        padding = 2;
        updateValues();
    }

    /**
     * Gets all registered entities and renders their sprites. If no sprite is registered, it renders the bounds instead
     */
    @Override
    public void update() {
        updateValues();
        batch.begin();
        int i=0;
        for (String key : debugValues.keySet()) {
            defaultFont.draw(batch, key + ": " + debugValues.get(key), padding, ZeroBit.targetHeight - i);
            i += 15;
        }
        batch.end();
    }

    /**
     * Access through {@link ZeroBit}.debugValue()
     */
    public void track(String name, Object value) {
        debugValues.put(name, String.valueOf(value));
    }

    protected void updateValues() {
        debugValues.put("FPS", String.valueOf(Gdx.graphics.getFramesPerSecond()));
        //debugValues.put("CPU", String.valueOf(ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime()));
        debugValues.put("Java Heap", String.valueOf(Math.round(Gdx.app.getJavaHeap() * 0.000001))+"MB");
        debugValues.put("Native Heap", String.valueOf(Math.round(Gdx.app.getNativeHeap() * 0.000001))+"MB");
        if (ZeroBit.isWorldSet()) {
            //debugValues.put("World Entities", String.valueOf(ZeroBit.getWorld().entities().getEntities().length));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        defaultFont.dispose();
        batch.dispose();
    }
}
