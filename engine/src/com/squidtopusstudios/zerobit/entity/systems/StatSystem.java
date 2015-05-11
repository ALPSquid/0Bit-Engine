package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.squidtopusstudios.zerobit.entity.components.MessagingComponent;
import com.squidtopusstudios.zerobit.entity.components.StatComponent;
import com.squidtopusstudios.zerobit.util.observers.ObserverKeys;

/**
 * Handles stat changes and creates observer messages
 */
public class StatSystem extends IteratingSystem {

    protected ComponentMapper<MessagingComponent> msgm = ComponentMapper.getFor(MessagingComponent.class);
    protected ComponentMapper<StatComponent> satm = ComponentMapper.getFor(StatComponent.class);
    protected MessagingComponent msgc;
    protected StatComponent statc;


    public StatSystem() {
        super(Family.all(StatComponent.class, MessagingComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        msgc = msgm.get(entity);
        statc = satm.get(entity);

        if (statc.healthChange != 0) {
            if (statc.healthChange < 0) damage(); else heal();
            statc.healthChange = 0;
            msgc.addObserverData(ObserverKeys.PLAYER_HEALTH_PERCENT, statc.healthPercent);
        }
        if (statc.xpChange != 0) {
            xpGain();
            statc.xpChange = 0;
            msgc.addObserverData(ObserverKeys.PLAYER_XP_PERCENT, statc.xpPercent);
        }
    }

    private void changeHealth(float amount) {
        statc.health += amount;
        statc.healthPercent = statc.health / statc.maxHealth;
    }

    /**
     * Deducts health, capping at 0
     */
    private void damage() {
        changeHealth((statc.health + statc.healthChange > 0)? statc.healthChange : -statc.health);
    }

    /**
     * Adds health, capping at max health
     */
    private void heal() {
        changeHealth((statc.health < statc.maxHealth - statc.healthChange) ? statc.healthChange : statc.maxHealth - statc.health);
    }

    /**
     * Increases XP, levelling up if full and adding the remainder
     */
    private void xpGain() {
        if (statc.xp + statc.xpChange >= statc.xpToLevel) {
            float remainder = (statc.xp + statc.xpChange) - statc.xpToLevel;
            statc.xp = 0 + remainder;
            statc.level++;
            msgc.addObserverData(ObserverKeys.PLAYER_LEVEL_UP, statc.level);
        } else {
            statc.xp += statc.xpChange;
        }
        statc.xpPercent = statc.xp / statc.xpToLevel;
    }
}
