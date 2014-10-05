package com.squidtopusstudios.zerobitengine.core.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;

import java.util.ArrayList;

/**
 * Default Renderer, for more control you can either extend this class or create your own that implements IRenderer
 */
public class DefaultRenderer implements IRenderer{

    protected OrthographicCamera camera;
    protected SpriteBatch batch;
    protected ShapeRenderer debugRenderer;
    protected ArrayList<ZbeEntity> debugEntities;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ZeroBit.width, ZeroBit.height);
        batch = new SpriteBatch();
        debugRenderer = new ShapeRenderer();
        debugEntities = new ArrayList<ZbeEntity>();
    }

    /**
     * Gets all registered entities and renders their sprites. If no sprite is registered, it renders the bounds instead
     */
    @Override
    public void update(float deltaTime) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        debugRenderer.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(ZeroBit.bg_colour.r, ZeroBit.bg_colour.g, ZeroBit.bg_colour.b, ZeroBit.bg_colour.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (ZbeEntity entity : ZeroBit.managers.entityManager().getEntities()) {
            if (entity.getSprite() == null) {
                debugEntities.add(entity);
            } else {
                batch.draw(entity.getSprite(), entity.getPosition().x, entity.getPosition().y,
                        entity.getBounds().width * ZeroBit.scale, entity.getBounds().height * ZeroBit.scale);
            }
        }
        batch.end();

        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (ZbeEntity debugEntity : debugEntities) {
            debugRenderer.setColor(debugEntity.getDebugColour());
            debugRenderer.rect(debugEntity.getBounds().x, debugEntity.getBounds().y,
                    debugEntity.getBounds().width * ZeroBit.scale, debugEntity.getBounds().height * ZeroBit.scale);
        }
        if (ZeroBit.DEBUG) {
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
}
