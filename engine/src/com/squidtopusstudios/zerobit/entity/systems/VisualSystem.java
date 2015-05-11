package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.squidtopusstudios.zerobit.entity.components.*;

/**
 * Updates animations and sprite positions
 */
public class VisualSystem extends IteratingSystem {

    protected ComponentMapper<TransformComponent> transm = ComponentMapper.getFor(TransformComponent.class);
    protected ComponentMapper<Box2DComponent> b2dm = ComponentMapper.getFor(Box2DComponent.class);
    protected ComponentMapper<VisualComponent> vism = ComponentMapper.getFor(VisualComponent.class);
    protected ComponentMapper<SpriterComponent> sprtm = ComponentMapper.getFor(SpriterComponent.class);
    protected ComponentMapper<MovementComponent> mvm = ComponentMapper.getFor(MovementComponent.class);
    protected TransformComponent transc;
    protected Box2DComponent b2dc;
    protected VisualComponent visc;
    protected SpriterComponent sprtc;
    protected MovementComponent mvc;



    public VisualSystem() {
        super(Family.all(VisualComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        transc = transm.get(entity);
        b2dc = b2dm.get(entity);
        visc = vism.get(entity);
        sprtc = sprtm.get(entity);
        mvc = mvm.get(entity);

        if (b2dc != null) {
            visc.sprite.setRotation(b2dc.body.getAngle() * MathUtils.radDeg);
            visc.sprite.setPosition(b2dc.body.getPosition().x, b2dc.body.getPosition().y);
            if (mvc != null) {
                visc.sprite.setFlip(visc.flipX, false);
                if (mvc.left) visc.flipX = true;
                else if (mvc.right) visc.flipX = false;
            }
            // Spriter
            if (sprtc != null) {
                sprtc.player.rotate(b2dc.body.getAngle() * MathUtils.radDeg);
                sprtc.player.setPosition(b2dc.body.getPosition().x, b2dc.body.getPosition().y);
                if ((visc.flipX && sprtc.player.flippedX() == 1) || (!visc.flipX && sprtc.player.flippedX() != 1)) sprtc.player.flipX();
            }
        }
        else if (transc != null) {
            visc.sprite.setRotation(transc.rotation);
            visc.sprite.setPosition(transc.x, transc.y);
            // Spriter
            if (sprtc != null) {
                sprtc.player.rotate(transc.rotation);
                sprtc.player.setPosition(transc.x, transc.y);
            }
        }
        if (visc.centerSprite) {
            visc.sprite.translate(-visc.sprite.getWidth()/2.0f, -visc.sprite.getHeight()/2.0f);
        }
    }
}
