package com.squidtopusstudios.zerobitengine.core.subsystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.squidtopusstudios.zerobitengine.World;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.components.ZbeEntityComponent;
import com.squidtopusstudios.zerobitengine.core.entity.systems.MetaSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.SpriteSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.ZbeSystem;
import com.squidtopusstudios.zerobitengine.utils.IManager;
import com.squidtopusstudios.zerobitengine.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper for LibGDX Ashley entity management system. An instance is registered to a {@link World}.
 */
public class EntityManager implements IManager {

    private static Map<World, EntityManager> entityManagerInstances = new HashMap<World, EntityManager>();
    private Engine ashleyInstance;
    private Family zbeFamily;
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
            ZeroBit.logger.logError("");
            return null;
        }
    }

    public void init() {
        if (!initialised){
            zbeFamily = Family.getFor(ZbeEntityComponent.class);
            ashleyInstance = new Engine();
            ashleyInstance.addSystem(new MetaSystem());
            ashleyInstance.addSystem(new SpriteSystem());
            initialised = true;
        } else {
            ZeroBit.logger.logError("EntityManager instance already initialised, you don't need to call init() manually");
        }
    }

    /**
     * Creates a new Ashley entity and adds it to the engine. Use for entities that don't require custom functionality.
     */
    public ZbeEntity createEntity(String name) {
        ZbeEntity entity = new ZbeEntity(name);
        addEntity(entity);
        return entity;
    }

    /**
     * Wrapper for ashley.addEntity().
     * Also adds the entity to registeredEntities for an Engine managed way of getting entities
     * @param entity Entity to add to engine
     */
    public void addEntity(ZbeEntity entity) {
        ZeroBit.logger.logDebug("Adding entity: '" + entity.getName() + "'");
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
     * Returns array of all registered {@link ZbeEntity} instances
     * @return {@link ZbeEntity} array
     */
    public ZbeEntity[] getEntities() {
        return ashleyInstance.getEntitiesFor(zbeFamily).toArray(ZbeEntity.class);
    }

    public Family getZbeFamily() {
        return zbeFamily;
    }

    /**
     * {@link ZbeSystem} wrapper for Ashley engine.getSystem()
     * @param systemType ZbeSystem.class to get
     * @return Registered ZbeSystem
     */
    public <T extends ZbeSystem> T getSystem(Class<T> systemType) {
        return ashleyInstance.getSystem(systemType);
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
        ZeroBit.logger.logDebug("Disposing");
        ZbeEntity[] familyEntities = ashleyInstance.getEntitiesFor(zbeFamily).toArray(ZbeEntity.class);
        for (ZbeEntity entity : familyEntities) {
            ZeroBit.logger.logDebug("Disposing Entity: " + entity.getName());
        }
        ashleyInstance.removeAllEntities();
    }
}
