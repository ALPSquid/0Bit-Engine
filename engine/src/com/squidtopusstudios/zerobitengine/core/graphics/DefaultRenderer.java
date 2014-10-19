package com.squidtopusstudios.zerobitengine.core.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

import java.util.ArrayList;

/**
 * Default Renderer, for more control you can either extend this class or create your own that implements IRenderer
 */
public class DefaultRenderer implements IRenderer{

    public ZbeOrthographicCamera camera;
    public Viewport viewport;
    protected SpriteBatch batch;
    protected ShapeRenderer debugRenderer;
    protected ArrayList<ZbeEntity> debugEntities;
    private float spriteX;
    private float spriteY;


    @Override
    public void create() {
        camera = new ZbeOrthographicCamera();
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
        camera.update();
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
                // Add offset in pixels relative to the original sprite dimensions.
                // For example, if the sprite is originally 16x16 but has been scaled in the game to 160x160
                // with a y offset of 1, we need to move the sprite up by 10 pixels as 1 visual sprite pixel = 10 real pixels
                spriteX = entity.getPosition().x + (entity.getSpriteOffset().x * (entity.getSpriteDimensions().width / entity.getSprite().getRegionWidth()));
                spriteY = entity.getPosition().y + (entity.getSpriteOffset().y * (entity.getSpriteDimensions().height / entity.getSprite().getRegionHeight()));
                switch (entity.getSpriteAlign()) {
                    case BOTTOM:
                        spriteX -= (entity.getSpriteDimensions().width - entity.getWidth()) / 2;
                        break;
                    case BOTTOM_LEFT:
                        // default
                        break;
                    case BOTTOM_RIGHT:
                        spriteX += entity.getWidth() - entity.getSpriteDimensions().width;
                        break;
                    case CENTER:
                        spriteX -= (entity.getSpriteDimensions().width - entity.getWidth()) / 2;
                        spriteY -= (entity.getSpriteDimensions().height - entity.getHeight()) / 2;
                        break;
                    case LEFT:
                        spriteY -= (entity.getSpriteDimensions().height - entity.getHeight()) / 2;
                        break;
                    case RIGHT:
                        spriteX += entity.getWidth() - entity.getSpriteDimensions().width;
                        spriteY -= (entity.getSpriteDimensions().height - entity.getHeight()) / 2;
                        break;
                    case TOP:
                        spriteX -= (entity.getSpriteDimensions().width - entity.getWidth()) / 2;
                        spriteY += entity.getHeight() - entity.getSpriteDimensions().height;
                        break;
                    case TOP_LEFT:
                        spriteY += entity.getHeight() - entity.getSpriteDimensions().height;
                        break;
                    case TOP_RIGHT:
                        spriteX += entity.getWidth() - entity.getSpriteDimensions().width;
                        spriteY += entity.getHeight() - entity.getSpriteDimensions().height;
                        break;
                }
                batch.draw(entity.getSprite(), spriteX, spriteY,
                        entity.getSpriteDimensions().width * ZeroBit.scale, entity.getSpriteDimensions().height * ZeroBit.scale);
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
