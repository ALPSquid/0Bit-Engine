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

    private ComponentMapper<TransformComponent> transm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<Box2DComponent> b2dm = ComponentMapper.getFor(Box2DComponent.class);
    private ComponentMapper<VisualComponent> vism = ComponentMapper.getFor(VisualComponent.class);
    private ComponentMapper<SpriterComponent> sprtm = ComponentMapper.getFor(SpriterComponent.class);
    private ComponentMapper<MovementComponent> mvm = ComponentMapper.getFor(MovementComponent.class);
    private TransformComponent transc;
    private Box2DComponent b2dc;
    private VisualComponent visc;
    private SpriterComponent sprtc;
    private MovementComponent mvc;



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
            visc.sprite.setPosition(b2dc.body.getPosition().x, b2dc.body.getPosition().y);
            visc.sprite.setRotation(b2dc.body.getAngle() * MathUtils.radDeg);
            if (mvc != null) {
                visc.sprite.setFlip(visc.flipX, false);
                if (mvc.left) visc.flipX = true;
                else if (mvc.right) visc.flipX = false;
            }
            // Spriter
            if (sprtc != null) {
                sprtc.player.setPosition(b2dc.body.getPosition().x, b2dc.body.getPosition().y);
                sprtc.player.rotate(b2dc.body.getAngle() * MathUtils.radDeg);
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
    }
}
