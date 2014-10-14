package com.squidtopusstudios.zerobitengine.core.subsystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.squidtopusstudios.zerobitengine.World;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.components.ZbeEntityComponent;
import com.squidtopusstudios.zerobitengine.core.entity.systems.*;
import com.squidtopusstudios.zerobitengine.utils.IManager;

import java.util.*;

/**
 * Wrapper for LibGDX Ashley entity management system. An instance is registered to a {@link World}.
 */
public class EntityManager implements IManager {

    private static Map<World, EntityManager> entityManagerInstances = new HashMap<World, EntityManager>();
    private Engine ashleyInstance;
    private Family zbeFamily;
    /** ZbeEntity Maps arranged by type **/
    private Map<String, List<ZbeEntity>> entities = new HashMap<String, List<ZbeEntity>>();
    private boolean initialised = false;


    private EntityManager() {}

    /**
     * Get the EntityManager instance registered to the provided {@link World} instance
     * @param world target {@link World} instance
     * @return {@link EntityManager} instance
     */
    public static EntityManager getInstance(World world) {
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
            ashleyInstance.addSystem(new SpriteSystem());
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
        ZbeEntity entity = new ZbeEntity();
        entity.setType(type);
        addEntity(entity);
        return entity;
    }

    /**
     * Wrapper for ashley.addEntity().
     * Also adds the entity to registeredEntities for an Engine managed way of getting entities
     * @param entity Entity to add to engine
     */
    public void addEntity(ZbeEntity entity) {
        ZeroBit.logger.logDebug("Adding entity: '" + entity.getType() + "'");
        if (entities.get(entity.getType()) == null) {
            entities.put(entity.getType(), new ArrayList<ZbeEntity>());
        }
        entities.get(entity.getType()).add(entity);
        ashleyInstance.addEntity(entity);
    }

    /**
     * Removes an entity from the World
     * @param entity ZbeEntity to remove
     */
    public void removeEntity(ZbeEntity entity) {
        entities.get(entity.getType()).remove(entity);
        ashleyInstance.removeEntity(entity);
    }

    /**
     * Returns array of all alive {@link ZbeEntity} instances
     * @return {@link ZbeEntity} array
     */
    public ZbeEntity[] getEntities() {
        return ashleyInstance.getEntitiesFor(zbeFamily).toArray(ZbeEntity.class);
    }

    /**
     * Returns List of all registered {@link ZbeEntity} by type
     * @param type Type of entity to get
     * @return List of {@link ZbeEntity}'s of type
     */
    public List<ZbeEntity> getEntitiesByType(String type) {
        return entities.get(type);
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
        ashleyInstance.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
        ZbeEntity[] familyEntities = ashleyInstance.getEntitiesFor(zbeFamily).toArray(ZbeEntity.class);
        for (ZbeEntity entity : familyEntities) {
            ZeroBit.logger.logDebug("Disposing Entity: " + entity.getType());
        }
        ashleyInstance.removeAllEntities();
    }
}
