package com.squidtopusstudios.zerobitengine.core.subsystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.utils.IManager;
import com.squidtopusstudios.zerobitengine.utils.Logger;

/**
 * Wrapper for LibGDX Ashley entity management system
 */
public class EntityManager implements IManager {

    private Engine ashleyInstance = new Engine();
    private static EntityManager entityManagerInstance;
    //private Map<String, Integer> registeredEntities = new HashMap<String, Integer>();

    private EntityManager() {}

    public static EntityManager getInstance() {
        if (entityManagerInstance == null) {
            entityManagerInstance = new EntityManager();
        }
        return entityManagerInstance;
    }

    /**
     * Creates a new Ashley entity and adds it to the engine
     */
    public void createEntity() {
        Entity entity = new Entity();
        addEntity(entity);
        //return entity;
    }

    /**
     * Wrapper for ashley.addEntity().
     * Also adds the entity to registeredEntities for an Engine managed way of getting entities
     * @param entity Entity to add to engine
     */
    public void addEntity(Entity entity) {
        ashleyInstance.addEntity(entity);
    }

    /**
     * Wrapper for ashley.removeEntity()
     * @param entity Entity to remove from engine
     */
    public void removeEntity(Entity entity) {
        ashleyInstance.removeEntity(entity);
    }

    /**
     * Wrapper for ashley.update()
     * @param deltaTime Gdx delta time
     */
    @Override
    public void update(float deltaTime) {
        ashleyInstance.update(deltaTime);
    }

    @Override
    public void dispose() {
        Logger.getInstance().logInfo("Disposing");
        ashleyInstance.removeAllEntities();
    }
}
