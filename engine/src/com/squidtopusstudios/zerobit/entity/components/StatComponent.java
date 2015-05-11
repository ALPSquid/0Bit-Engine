package com.squidtopusstudios.zerobit.entity.components;

/**
 * Holds stat data
 */
public class StatComponent extends ZBComponent {

    public float healthChange = 0; // Amount of health to add/subtract this tick
    public float maxHealth = 1000;
    public float health = maxHealth;
    public float healthPercent = 1;

    public float xpChange = 0; // Amount of xp to add this tick
    public float xp = 0;
    public float xpPercent = 0;
    public float xpToLevel = 100;
    public int level = 1;

    public float attack = 10;

    @Override
    public void reset() {

    }
}
