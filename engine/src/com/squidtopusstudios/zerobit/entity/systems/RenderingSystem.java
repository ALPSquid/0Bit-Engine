package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.entity.components.*;
import com.squidtopusstudios.zerobit.entity.components.ai.BehaviourComponent;
import com.squidtopusstudios.zerobit.entity.components.ai.NodeComponent;
import com.squidtopusstudios.zerobit.util.comparators.ZIndexComparator;

import java.util.ArrayList;


/**
 * Renders entity sprites
 */
public class RenderingSystem extends SortedIteratingSystem {

    private ComponentMapper<VisualComponent> vism = ComponentMapper.getFor(VisualComponent.class);
    private ComponentMapper<SpriterComponent> sprtm = ComponentMapper.getFor(SpriterComponent.class);
    private ComponentMapper<BehaviourComponent> bvm = ComponentMapper.getFor(BehaviourComponent.class);
    private ComponentMapper<NodeComponent> nodem = ComponentMapper.getFor(NodeComponent.class);
    VisualComponent visc;
    SpriterComponent sprtc;
    BehaviourComponent bvc;
    NodeComponent nodec;

    public float[] bgColour = {0, 0, 0, 0};
    private ArrayList<ParticleEffect> particles = new ArrayList<ParticleEffect>();
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;


    public RenderingSystem() {
        super(Family.all(VisualComponent.class).get(), new ZIndexComparator());
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClearColor(bgColour[0], bgColour[1], bgColour[2], bgColour[3]);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        super.update(deltaTime);
        for (ParticleEffect particle : particles) particle.draw(batch, deltaTime);
        batch.end();

        if (ZeroBit.debugEnabled) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                for (Entity entity : getEntities()) debugRender(entity);
            shapeRenderer.end();
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        render(entity);
    }

    private void render(Entity entity) {
        visc = vism.get(entity);
        sprtc = sprtm.get(entity);
        nodec = nodem.get(entity);
        if (sprtc != null) {
            sprtc.player.update();
            sprtc.drawer.draw(sprtc.player, batch);
        } else {
            if (nodec == null || ZeroBit.debugRender) visc.sprite.draw(batch);
        }
    }

    private void debugRender(Entity entity) {
        bvc = bvm.get(entity);
        if (bvc != null) bvc.controller.debugRender(shapeRenderer);
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        super.removedFromEngine(engine);
        for (ParticleEffect particle : particles) particle.dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }

    /**
     * Add a PartcleEffect to be rendered
     * @param particleEffect ParticleEffect to add
     */
    public void addParticle(ParticleEffect particleEffect) {
        particles.add(particleEffect);
    }

    /**
     * @param camera camera to use for the Box2D debug renderer and lights
     */
    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

}
