package com.squidtopusstudios.zerobitengine.core.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

import java.util.ArrayList;

/**
 * Default Renderer, for more control you can either extend this class or create your own that implements IRenderer
 */
public class DefaultRenderer implements IRenderer{

    public OrthographicCamera camera;
    public Viewport viewport;
    protected SpriteBatch batch;
    protected ShapeRenderer debugRenderer;
    protected ArrayList<ZbeEntity> debugEntities;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ZeroBit.targetWidth, ZeroBit.targetHeight);
        camera.update();
        viewport = new FitViewport(ZeroBit.targetWidth, ZeroBit.targetHeight, camera);
        batch = new SpriteBatch();
        debugRenderer = new ShapeRenderer();
        debugEntities = new ArrayList<ZbeEntity>();
    }

    /**
     * Gets all registered entities and renders their sprites. If no sprite is registered, it renders the bounds instead
     */
    @Override
    public void update() {
        //camera.update();
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        debugRenderer.setProjectionMatrix(camera.projection);
        debugRenderer.setTransformMatrix(camera.view);

        Gdx.gl.glClearColor(ZeroBit.bg_colour.r, ZeroBit.bg_colour.g, ZeroBit.bg_colour.b, ZeroBit.bg_colour.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // Entities
        for (ZbeEntity entity : ZeroBit.managers.entityManager().getEntities()) {
            if (entity.getSprite() == null) {
                debugEntities.add(entity);
            } else {
                batch.draw(entity.getSprite(), entity.getPosition().x - (entity.getSpriteDimenstions().width - entity.getBounds().width)/2,
                        entity.getPosition().y - (entity.getSpriteDimenstions().height - entity.getBounds().height)/2,
                        entity.getSpriteDimenstions().width * ZeroBit.scale, entity.getSpriteDimenstions().height * ZeroBit.scale);
            }
        }
        batch.end();

        // Viewport bars
        if (viewport.getBottomGutterHeight() > 0 || viewport.getLeftGutterWidth() > 0) {
            Texture borderTexture = ZeroBit.managers.resourceManager().getResource("viewport_border");
            int screenWidth = Gdx.graphics.getWidth();
            int screenHeight = Gdx.graphics.getHeight();
            Gdx.gl.glViewport(0, 0, screenWidth, screenHeight);
            batch.getProjectionMatrix().idt().setToOrtho2D(0, 0, screenWidth, screenHeight);
            batch.getTransformMatrix().idt();
            batch.begin();
            if (viewport.getLeftGutterWidth() > 0) {
                batch.draw(borderTexture, 0, 0, viewport.getLeftGutterWidth(), screenHeight);
                batch.draw(borderTexture, viewport.getRightGutterX(), 0, viewport.getRightGutterWidth(), screenHeight);
            }
            if (viewport.getBottomGutterHeight() > 0) {
                batch.draw(borderTexture, 0, 0, screenWidth, viewport.getBottomGutterHeight());
                batch.draw(borderTexture, 0, viewport.getTopGutterY(), screenWidth, viewport.getTopGutterHeight());
            }
            batch.end();
            viewport.update(screenWidth, screenHeight, true);
        }

        // Debug
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (ZbeEntity debugEntity : debugEntities) {
            debugRenderer.setColor(debugEntity.getDebugColour());
            debugRenderer.rect(debugEntity.getBounds().x, debugEntity.getBounds().y,
                    debugEntity.getBounds().width * ZeroBit.scale, debugEntity.getBounds().height * ZeroBit.scale);
        }
        if (ZeroBit.showDebugRenderer) {
            for (ZbeEntity entity : ZeroBit.managers.entityManager().getEntities()) {
                debugRenderer.setColor(entity.getDebugColour());
                debugRenderer.rect(entity.getBounds().x, entity.getBounds().y,
                        entity.getBounds().width * ZeroBit.scale, entity.getBounds().height * ZeroBit.scale);
            }
        }
        debugRenderer.end();
    }

    @Override
    public void dispose() {
        debugRenderer.dispose();
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
