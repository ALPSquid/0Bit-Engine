package com.squidtopusstudios.zerobitengine.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.squidtopusstudios.zerobitengine.WorldB2D;
import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntityB2D;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntity;

import java.util.ArrayList;

/**
 * Default Renderer, for more control you can either extend this class or create your own that implements IRenderer
 */
public class DefaultRenderer implements Renderer {

    public ZbeOrthographicCamera camera;
    public Viewport viewport;
    protected SpriteBatch batch;
    protected ShapeRenderer debugRenderer;
    protected Box2DDebugRenderer b2DebugRenderer;
    protected Matrix4 b2Matrix;
    protected ArrayList<ZbeEntity> debugEntities;
    private float spriteX;
    private float spriteY;
    private float batchOffsetX = 0;
    private float batchOffsetY = 0;
    private PolygonSpriteBatch polyBatch;


    @Override
    public void create() {
        camera = new ZbeOrthographicCamera();
        camera.setToOrtho(false, ZeroBit.targetWidth, ZeroBit.targetHeight);
        camera.update();
        viewport = new FitViewport(ZeroBit.targetWidth, ZeroBit.targetHeight, camera);
        batch = new SpriteBatch();
        debugRenderer = new ShapeRenderer();
        b2DebugRenderer = new Box2DDebugRenderer();
        b2Matrix = new Matrix4(camera.combined);
        debugEntities = new ArrayList<ZbeEntity>();
        polyBatch = new PolygonSpriteBatch();
    }

    /**
     * Gets all registered entities and renders their sprites. If no sprite is registered, it renders the bounds instead
     */
    @Override
    public void update() {
        /*camera.update();
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        debugRenderer.setProjectionMatrix(camera.projection);
        debugRenderer.setTransformMatrix(camera.view);
        b2Matrix.set(camera.combined);
        b2Matrix.scale(ZeroBit.getWorld().getPixelsPerUnit(), ZeroBit.getWorld().getPixelsPerUnit(), 1);
        if (!camera.isFollowing()) {
            b2Matrix.translate((camera.viewportWidth / ZeroBit.getWorld().getPixelsPerUnit()) / 2, 0, 0);
            batchOffsetX = camera.viewportWidth / 2;
        } else if (batchOffsetX > 0) {
            batchOffsetX = 0;
        }

        Gdx.gl.glClearColor(ZeroBit.bg_colour.r, ZeroBit.bg_colour.g, ZeroBit.bg_colour.b, ZeroBit.bg_colour.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO Test and remove (Rube Test)
        for (ZbeEntity entity : ZeroBit.managers.entityManager().getEntities()) {
            if (entity.getSprite().getTexture() == null) {
                if (entity.isBox2D() && ((ZbeEntityB2D) entity).polygonRegion != null) {
                    polyBatch.setProjectionMatrix(camera.combined);
                    polyBatch.begin();
                    polyBatch.draw(((ZbeEntityB2D) entity).polygonRegion, entity.getX(), entity.getY());
                    polyBatch.end();
                }
            }
        }
        //---

        batch.begin();
        // Entities
        for (ZbeEntity entity : ZeroBit.managers.entityManager().getEntities()) {
            if (entity.getSprite().getTexture() == null) {
                // TODO change automatic debug design to improve performance
                debugEntities.add(entity); // TODO option to disable this behaviour for an entity
            } else {
                // Add offset in pixels relative to the original sprite dimensions.
                // For example, if the sprite is originally 16x16 but has been scaled in the game to 160x160
                // with a y offset of 1, we need to move the sprite up by 10 pixels as 1 visual sprite pixel = 10 real pixels
                spriteX = entity.getBounds().x + (entity.getSpriteOffset().x * (entity.getSprite().getWidth() / entity.getSprite().getRegionWidth()));
                spriteY = entity.getBounds().y + (entity.getSpriteOffset().y * (entity.getSprite().getHeight() / entity.getSprite().getRegionHeight()));
                switch (entity.getSpriteAlign()) {
                    case BOTTOM:
                        spriteX -= (entity.getSprite().getWidth() - entity.getWidth()) / 2;
                        break;
                    case BOTTOM_LEFT:
                        // default
                        break;
                    case BOTTOM_RIGHT:
                        spriteX += entity.getWidth() - entity.getSprite().getWidth();
                        break;
                    case CENTER:
                        spriteX -= (entity.getSprite().getWidth() - entity.getWidth()) / 2;
                        spriteY -= (entity.getSprite().getHeight() - entity.getHeight()) / 2;
                        break;
                    case LEFT:
                        spriteY -= (entity.getSprite().getHeight() - entity.getHeight()) / 2;
                        break;
                    case RIGHT:
                        spriteX += entity.getWidth() - entity.getSprite().getWidth();
                        spriteY -= (entity.getSprite().getHeight() - entity.getHeight()) / 2;
                        break;
                    case TOP:
                        spriteX -= (entity.getSprite().getWidth() - entity.getWidth()) / 2;
                        spriteY += entity.getHeight() - entity.getSprite().getHeight();
                        break;
                    case TOP_LEFT:
                        spriteY += entity.getHeight() - entity.getSprite().getHeight();
                        break;
                    case TOP_RIGHT:
                        spriteX += entity.getWidth() - entity.getSprite().getWidth();
                        spriteY += entity.getHeight() - entity.getSprite().getHeight();
                        break;
                }
                entity.getSprite().setPosition(spriteX + batchOffsetX, spriteY + batchOffsetY);
                entity.getSprite().setScale(ZeroBit.scale);
                if (entity.isBox2D()) {
                    if (entity.autoOrigin() && ((ZbeEntityB2D)entity).getBoundsOrigin().equals(ZeroBit.BoundsOrigin.CENTER)) entity.getSprite().setOriginCenter();
                    if (entity.autoRotate()) entity.getSprite().setRotation(((ZbeEntityB2D) entity).getBoundsAnchor().getAngle() * MathUtils.radDeg);
                }
                entity.getSprite().draw(batch);
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
        if (ZeroBit.showDebugRenderer) {
            debugRender(ZeroBit.managers.entityManager().getEntities());
            if (ZeroBit.getWorld().getWorldType().equals(ZeroBit.WorldType.BOX2D))
                b2DebugRenderer.render(((WorldB2D)ZeroBit.getWorld()).getB2DWorld(), b2Matrix);
        }
        else {
            // Debug render everything without a sprite
            //debugRender(debugEntities.toArray(new ZbeEntityBase[debugEntities.size()]));
        }
        debugRenderer.end();*/
    }

    /*public void debugRender(ZbeEntity... entities) {
        for (ZbeEntity entity : entities) {
            debugRenderer.setColor(entity.getDebugColour());
            if (entity.isBox2D()) {
                ZbeEntityB2D zbeEntityB2D = ((ZbeEntityB2D)entity);
                debugRenderer.setColor(Color.GREEN);
                debugRenderer.circle(ZeroBit.getWorld().unitsToPixels(zbeEntityB2D.getPosition().x) + batchOffsetX, ZeroBit.getWorld().unitsToPixels(zbeEntityB2D.getPosition().y) + batchOffsetY, 3);
                debugRenderer.setColor(Color.BLUE);
                debugRenderer.circle(ZeroBit.getWorld().unitsToPixels(zbeEntityB2D.getBody().getWorldCenter().x) + batchOffsetX, ZeroBit.getWorld().unitsToPixels(zbeEntityB2D.getBody().getWorldCenter().y) + batchOffsetY, 3);

                debugRenderer.setColor(entity.getDebugColour());
                debugRenderer.circle(zbeEntityB2D.getBounds().x + batchOffsetX, zbeEntityB2D.getBounds().y + batchOffsetY, 3);

                debugRenderer.rect(zbeEntityB2D.getBounds().x + batchOffsetX, zbeEntityB2D.getBounds().y + batchOffsetY,
                        (zbeEntityB2D.getBoundsOrigin().equals(ZeroBit.BoundsOrigin.BOTTOM_LEFT))? 0 : zbeEntityB2D.getWidth()/2 + zbeEntityB2D.getBoundsOffset().x,
                        (zbeEntityB2D.getBoundsOrigin().equals(ZeroBit.BoundsOrigin.BOTTOM_LEFT))? 0 : zbeEntityB2D.getHeight()/2 + zbeEntityB2D.getBoundsOffset().y,
                        zbeEntityB2D.getBounds().width, zbeEntityB2D.getBounds().height,
                        ZeroBit.scale, ZeroBit.scale, zbeEntityB2D.getBoundsAnchor().getAngle() * MathUtils.radDeg);

            } else {
                debugRenderer.rect(entity.getBounds().x+batchOffsetX, entity.getBounds().y+batchOffsetY,
                        entity.getBounds().width * ZeroBit.scale, entity.getBounds().height * ZeroBit.scale);
            }
        }
    }*/

    public void updateB2dMatrix(boolean isBox2D) {
        //b2Matrix.scl(ZeroBit.getWorld().getPixelsPerUnit());
        //b2Matrix.translate((camera.viewportWidth/ZeroBit.getWorld().getPixelsPerUnit())/2, 0, 0);
        if (isBox2D) {
            batchOffsetX = camera.viewportWidth/2;
        } else {
            batchOffsetX = 0;
        }
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
