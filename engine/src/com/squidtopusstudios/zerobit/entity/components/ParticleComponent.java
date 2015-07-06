package com.squidtopusstudios.zerobit.entity.components;


import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import java.util.ArrayList;
import java.util.List;

/**
 * Component for attaching particle effects to entities
 */
public class ParticleComponent extends ZBComponent {

    private List<ParticleEffect> particles = new ArrayList<ParticleEffect>();


    /** Adds a particle effect to be rendered */
    public void addParticle(ParticleEffect particle) {
        particles.add(particle);
    }

    /** Removes a particle effect */
    public void removeParticle(ParticleEffect particle) {
        particles.remove(particle);
    }

    /**
     * @return List of added particle effects
     */
    public List<ParticleEffect> getParticles() {
        return particles;
    }

    @Override
    public void reset() {

    }
}
