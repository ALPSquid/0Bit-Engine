package com.squidtopusstudios.zerobitengine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.squidtopusstudios.zerobitengine.entity.DynamicEntity;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntityB2D;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.entity.components.ZbeEntityComponent;
import com.squidtopusstudios.zerobitengine.entity.systems.*;

import java.util.*;

/**
 * Entity manager built on the LibGDX Ashley entity management system. Each {@link World} has it's own instance.
 */
public class EntityManager {
    
    private Engine ashleyInstance;
    /** ZbeEntity Maps arranged by user defined type **/
    private Map<String, List<ZbeEntity>> entities = new HashMap<String, List<ZbeEntity>>();


    public EntityManager() {
        ashleyInstance = new Engine();
        // TODO Decide on the best way of adding EntitySystems
        ashleyInstance.addSystem(new SpriteSystem());
        ashleyInstance.addSystem(new SpriteAnimationSystem());
        ashleyInstance.addSystem(new PhysicsSystem());
        ashleyInstance.addSystem(new MovementSystem());
        ashleyInstance.addSystem(new CollisionSystem());
        ashleyInstance.addSystem(new LogicSystem());
        ashleyInstance.addSystem(new StateSystem());
    }

    // TODO Use an Abstract Factory for creating Entities
    /**
     * Creates a new Ashley entity and adds it to the engine. Use for entities that don't require custom functionality.
     */
    public DynamicEntity createEntity(String type) {
        DynamicEntity entity = new DynamicEntity(type);
        addEntity(entity);
        return entity;
    }
    /**
     * Creates a new Ashley Box2D entity and adds it to the engine. Use for entities that don't require custom functionality.
     */
    /*public ZbeEntityB2D createEntityB2D(String type) {
        ZbeEntityB2D entity = new ZbeEntityB2D(type);
        addEntity(entity);
        return entity;
    }*/

    /**
     * Wrapper for ashley.addEntity().
     * Also adds the entity to registeredEntities for an Engine managed way of getting entities
     * @param entity Entity to add to engine
     */
    public <T extends ZbeEntity> void addEntity(T entity) {
        /*ZeroBit.logger.logDebug("Adding entity: '" + entity.getType() + "'");
        if (entities.get(entity.getType()) == null) {
            entities.put(entity.getType(), new ArrayList<ZbeEntity>());
        }
        entities.get(entity.getType()).add(entity);*/
        entity.create();
        ashleyInstance.addEntity(entity);
    }

    /**
     * Removes an entity from the World
     * @param entity ZbeEntity to remove
     */
    public void removeEntity(ZbeEntity entity) {
        //entities.get(entity.getType()).remove(entity);
        entity.dispose();
        ashleyInstance.removeEntity(entity);
    }

    /**
     * Returns array of all alive <T extends {@link com.squidtopusstudios.zerobitengine.entity.ZbeEntity}> instances
     * @return array of T extends ZbeEntityBase
     */
    /*public <T extends ZbeEntity> T[] getEntitiesAs(Class<T> entityClass) {
        return ashleyInstance.getEntitiesFor(zbeFamily).toArray(entityClass);
    }*/
    /**
     * Returns array of all alive {@link com.squidtopusstudios.zerobitengine.entity.ZbeEntity} instances
     * @return {@link com.squidtopusstudios.zerobitengine.entity.ZbeEntity} array
     */
    /*public ZbeEntity[] getEntities() {
        return getEntitiesAs(ZbeEntity.class);
    }*/

    /**
     * Returns List of all registered <T extends ZbeEntityBase> by type
     * @param type Type of entity to get
     * @return List of {@link com.squidtopusstudios.zerobitengine.entity.DynamicEntity}'s of type
     */
    public <T extends ZbeEntity> List<T> getEntitiesByTypeAs(String type, Class<T> entityClass) {
        return (List<T>)entities.get(type);
    }
    /**
     * Returns List of all registered {@link com.squidtopusstudios.zerobitengine.entity.ZbeEntity} by type
     * @param type Type of entity to get
     * @return List of {@link com.squidtopusstudios.zerobitengine.entity.DynamicEntity}'s of type
     */
    public List<ZbeEntity> getEntitiesByType(String type) {
        return getEntitiesByTypeAs(type, ZbeEntity.class);
    }

    /**
     * Add a system to the Ashley engine instance.
     * Use this to add your own systems and use getSystem() to get them.
     * @param system System instance to add
     */
    public void addSystem(ZbeSystem system) {
        ashleyInstance.addSystem(system);
    }

    /**
     * @param systemType ZbeSystem.class to get
     * @return Registered {@link ZbeSystem}
     */
    public <T extends ZbeSystem> T getSystem(Class<T> systemType) {
        return ashleyInstance.getSystem(systemType);
    }

    /** Updates the Ashley instance */
    public void update() {
        ashleyInstance.update(ZeroBit.getDelta());
    }

    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
        /*ZbeEntity[] entities = getEntitiesAs(ZbeEntity.class);
        for (ZbeEntity entity : entities) {
            ZeroBit.logger.logDebug("Disposing Entity: " + entity.getType());
            entity.dispose();
        }*/
        ashleyInstance.removeAllEntities();
    }
}
