package com.squidtopusstudios.zerobit.ui.actors;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Based on: https://stackoverflow.com/questions/27083744/libgdx-particles-do-not-show
 */
public class ParticleEffectActor extends Actor {

    private ParticleEffect effect;


    public ParticleEffectActor(FileHandle particleFile, TextureAtlas atlas) {
        super();
        effect = new ParticleEffect();
        effect.load(particleFile, atlas);
    }

    @Override
    public void scaleBy(float scaleFactor){
        effect.scaleEffect(scaleFactor);
    }

    @Override
    public void setPosition(float x, float y){
        super.setPosition(x, y);
        effect.setPosition(x, y);
    }

    public void start() {
        effect.start();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        effect.update(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        effect.draw(batch);
    }

    public ParticleEffect getEffect(){ return this.effect; }
}