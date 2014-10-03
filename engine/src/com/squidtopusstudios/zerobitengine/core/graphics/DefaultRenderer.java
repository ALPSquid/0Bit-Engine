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

    @Override
    public void update(float deltaTime) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        debugRenderer.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        /** Get all registered entities and render their sprites. If no sprite is registered, render a debug rect instead */
        for (ZbeEntity entity : ZeroBit.managers.entityManager().getEntities()) {
            if (entity.getSprite() == null) {
                debugEntities.add(entity);
            } else {
                batch.draw(entity.getSprite(), (camera.viewportWidth/2) - entity.getBounds().width/2, (camera.viewportHeight/2) - entity.getBounds().height/2,
                        entity.getBounds().width, entity.getBounds().height);
            }
        }
        batch.end();

        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(Color.RED);
        for (ZbeEntity debugEntity : debugEntities) {
            debugRenderer.rect((camera.viewportWidth/2) - debugEntity.getBounds().width/2, (camera.viewportHeight/2) - debugEntity.getBounds().height/2,
                    debugEntity.getBounds().width, debugEntity.getBounds().height);
        }
        debugRenderer.end();
    }

    @Override
    public void dispose() {
        debugRenderer.dispose();
        batch.dispose();
    }
}
