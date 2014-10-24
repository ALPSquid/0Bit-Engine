package com.squidtopusstudios.zerobitengine.core.subsystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.squidtopusstudios.zerobitengine.World;
import com.squidtopusstudios.zerobitengine.WorldBase;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityB2D;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;
import com.squidtopusstudios.zerobitengine.core.entity.components.Box2DComponent;
import com.squidtopusstudios.zerobitengine.core.entity.components.ZbeEntityComponent;
import com.squidtopusstudios.zerobitengine.core.entity.systems.*;
import com.squidtopusstudios.zerobitengine.utils.IManager;

import java.util.*;

/**
 * Wrapper for LibGDX Ashley entity management system. An instance is registered to a {@link World}.
 */
public class EntityManager implements IManager {

    private static Map<WorldBase, EntityManager> entityManagerInstances = new HashMap<WorldBase, EntityManager>();
    private Engine ashleyInstance;
    private Family zbeFamily;
    /** ZbeEntity Maps arranged by type **/
    //private Map<String, List<ZbeEntity>> entities = new HashMap<String, List<ZbeEntity>>();
    private Map<String, List<ZbeEntityBase>> entities = new HashMap<String, List<ZbeEntityBase>>();
    //private Map<String, List<ZbeEntityB2D>> b2dEntities = new HashMap<String, List<ZbeEntityB2D>>();
    private boolean initialised = false;


    private EntityManager() {}

    /**
     * Get the EntityManager instance registered to the provided {@link World} instance
     * @param world target {@link World} instance
     * @return {@link EntityManager} instance
     */
    public static EntityManager getInstance(WorldBase world) {
        if (world != null) {
            if (entityManagerInstances.get(world) == null) {
                entityManagerInstances.put(world, new EntityManager());
                entityManagerInstances.get(world).init();
            }
            return entityManagerInstances.get(world);
        } else {
            ZeroBit.logger.logError("No World set");
            return null;
        }
    }

    public void init() {
        if (!initialised){
            zbeFamily = Family.getFor(ZbeEntityComponent.class);
            ashleyInstance = new Engine();
            // Choose families
            ashleyInstance.addSystem(new SpriteSystem());
            ashleyInstance.addSystem(new SpriteAnimationSystem());
            ashleyInstance.addSystem(new PhysicsSystem());
            ashleyInstance.addSystem(new MovementSystem());
            ashleyInstance.addSystem(new CollisionSystem());
            ashleyInstance.addSystem(new LogicSystem());
            ashleyInstance.addSystem(new StateSystem());
            initialised = true;
        } else {
            ZeroBit.logger.logError("EntityManager instance already initialised, you don't need to call init() manually");
        }
    }

    /**
     * Creates a new Ashley entity and adds it to the engine. Use for entities that don't require custom functionality.
     */
    public ZbeEntity createEntity(String type) {
        ZbeEntity entity = new ZbeEntity(type);
        addEntity(entity);
        return entity;
    }

    /**
     * Creates a new Ashley Box2D entity and adds it to the engine. Use for entities that don't require custom functionality.
     */
    public ZbeEntityB2D createEntityB2D(String type) {
        ZbeEntityB2D entity = new ZbeEntityB2D(type);
        addEntity(entity);
        return entity;
    }

    /**
     * Wrapper for ashley.addEntity().
     * Also adds the entity to registeredEntities for an Engine managed way of getting entities
     * @param entity Entity to add to engine
     */
    public <T extends ZbeEntityBase> void addEntity(T entity) {
        //if (entity.getClass().equals(ZbeEntity.class)) {
        if (entities.get(entity.getType()) == null) {
            entities.put(entity.getType(), new ArrayList<ZbeEntityBase>());
        }
        entities.get(entity.getType()).add(entity);
        /*}
        else if (entity.getClass().equals(ZbeEntityB2D.class)) {
            if (b2dEntities.get(entity.getType()) == null) {
                b2dEntities.put(entity.getType(), new ArrayList<ZbeEntityB2D>());
            }
            b2dEntities.get(((ZbeEntityB2D) entity).getType()).add(((ZbeEntityB2D) entity));
        }*/
        ZeroBit.logger.logDebug("Adding entity: '" + entity.getType() + "'");
        ashleyInstance.addEntity(entity);
    }

    /**
     * Removes an entity from the World
     * @param entity ZbeEntity to remove
     */
    public void removeEntity(ZbeEntityBase entity) {
        entities.get(entity.getType()).remove(entity);
        ashleyInstance.removeEntity(entity);
    }

    /**
     * Returns array of all alive {@link ZbeEntityBase} instances
     * @return {@link ZbeEntity} array
     */
    public ZbeEntityBase[] getEntities() {
        return ashleyInstance.getEntitiesFor(zbeFamily).toArray(ZbeEntityBase.class);
    }
    /**
     * Returns array of all alive <T extends ZbeEntityBase> instances
     * @return {@link ZbeEntity} array
     */
    public <T extends ZbeEntityBase> T[] getEntitiesAs(Class<T> entityClass) {
        return ashleyInstance.getEntitiesFor(zbeFamily).toArray(entityClass);
    }

    /**
     * Returns List of all registered {@link ZbeEntityBase} by type
     * @param type Type of entity to get
     * @return List of {@link ZbeEntity}'s of type
     */
    public List<ZbeEntityBase> getEntitiesByType(String type) {
        return entities.get(type);
    }
    /**
     * Returns List of all registered <T extends ZbeEntityBase> by type
     * @param type Type of entity to get
     * @return List of {@link ZbeEntity}'s of type
     */
    public <T extends ZbeEntityBase> List<T> getEntitiesByTypeAs(String type, Class<T> entityClass) {
        return (List<T>)entities.get(type);
    }

    public Family getZbeFamily() {
        return zbeFamily;
    }

    /**
     * Add a system to the current Ashley engine instance.
     * Use this to add your own systems and use getSystem() to get them.
     * @param system System instance to add
     */
    public void addSystem(ZbeSystem system) {
        ashleyInstance.addSystem(system);
    }

    /**
     * {@link ZbeSystem} wrapper for Ashley engine.getSystem()
     * @param systemType ZbeSystem.class to get
     * @return Registered {@link ZbeSystem}
     */
    public <T extends ZbeSystem> T getSystem(Class<T> systemType) {
        return ashleyInstance.getSystem(systemType);
    }

    /**
     * Wrapper for ashley.update()
     */
    @Override
    public void update() {
        ashleyInstance.update(ZeroBit.getDelta());
    }

    @Override
    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
        ZbeEntityBase[] familyEntities = ashleyInstance.getEntitiesFor(zbeFamily).toArray(ZbeEntityBase.class);
        for (ZbeEntityBase entity : familyEntities) {
            ZeroBit.logger.logDebug("Disposing Entity: " + entity.getType());
        }
        ashleyInstance.removeAllEntities();
    }
}
