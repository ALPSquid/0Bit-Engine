package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.entity.components.TransformComponent;
import com.squidtopusstudios.zerobit.entity.components.VisualComponent;;

/**
 * Manages day and night cycle
 */
public class DaySystem extends EntitySystem {

    protected float skyDayAlpha = 1;
    protected double alpha;
    protected int speed = 256; // 256
    protected boolean toDay = false; // Transition: to day=1, to night=0
    protected Entity skyboxDay;
    protected Entity skyboxNight;
    protected Box2DSystem b2dSystem;

    protected ComponentMapper<TransformComponent> transm = ComponentMapper.getFor(TransformComponent.class);
    protected ComponentMapper<VisualComponent> vism = ComponentMapper.getFor(VisualComponent.class);
    protected TransformComponent day_transc;
    protected TransformComponent night_transc;
    protected VisualComponent day_visc;
    protected VisualComponent night_visc;


    public DaySystem() {
    }

    @Override
    public void update(float deltaTime) {
        double gameTimeStep = deltaTime / speed;
        if (!toDay) ZeroBit.gameTime += gameTimeStep;
        else ZeroBit.gameTime -= gameTimeStep;

        // Rotation
        /*double rotationStep = 180 * gameTimeStep;
        day_transc.rotation -= rotationStep;
        night_transc.rotation -= rotationStep;
        if (day_transc.rotation >= 360) day_transc.rotation = 0;
        if (night_transc.rotation >= 360) night_transc.rotation = 0;*/
        float rotation;
        if (!toDay) {
            rotation = 180 * ZeroBit.gameTime + 90;
        } else {
            if (ZeroBit.gameTime > 0) {
                rotation = 360 - (180 * ZeroBit.gameTime - 90);
            } else {
                rotation = 180 * ZeroBit.gameTime;
            }
        }
        day_transc.rotation = -rotation;
        night_transc.rotation = -rotation;

        if (ZeroBit.gameTime <= 0 && toDay) toDay = false;
        else if (ZeroBit.gameTime >= 1 && !toDay) toDay = true;


        // Skybox Fading
        skyDayAlpha = 1 - ZeroBit.gameTime;
        if (skyDayAlpha < 0) skyDayAlpha = 0;
        if (skyDayAlpha > 1) skyDayAlpha = 1;
        day_visc.sprite.setAlpha(skyDayAlpha);


        // Sun alpha
        alpha = 1 - ZeroBit.gameTime;
        if (alpha < ZeroBit.ambientAlphaNight) alpha = ZeroBit.ambientAlphaNight;
        else if (alpha > ZeroBit.ambientAlphaDay) alpha = ZeroBit.ambientAlphaDay;
        //if (alpha >= WKWorld.AMBIENT_ALPHA_DAY && toDay) toDay = false;
        //else if (alpha <= WKWorld.AMBIENT_ALPHA_NIGHT && !toDay) toDay = true;
        if (b2dSystem != null) b2dSystem.getRayHandler().setAmbientLight(b2dSystem.ambientColour.r, b2dSystem.ambientColour.g, b2dSystem.ambientColour.b, (float)alpha);
    }

    public void setB2dSystem(Box2DSystem b2dSystem) {
        this.b2dSystem = b2dSystem;
    }

    public void setSkyBoxes(Entity skyboxDay, Entity skyboxNight) {
        this.skyboxDay = skyboxDay;
        this.skyboxNight = skyboxNight;

        day_transc = transm.get(skyboxDay);
        night_transc = transm.get(skyboxNight);
        day_visc = vism.get(skyboxDay);
        night_visc = vism.get(skyboxNight);

        day_transc.x += day_visc.sprite.getWidth()/2 * day_visc.sprite.getScaleX();
        day_transc.y += day_visc.sprite.getHeight()/2 * day_visc.sprite.getScaleY();
        day_visc.sprite.setOriginCenter();
        day_visc.sprite.setPosition(day_transc.x, day_transc.y);

        night_transc.x += night_visc.sprite.getWidth()/2 * night_visc.sprite.getScaleX();
        night_transc.y += night_visc.sprite.getHeight()/2 * night_visc.sprite.getScaleX();
        night_visc.sprite.setOriginCenter();
        night_visc.sprite.setPosition(night_transc.x, night_transc.y);
    }
}
