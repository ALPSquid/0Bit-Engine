package com.squidtopusstudios.zerobitengine.refact.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.squidtopusstudios.zerobitengine.refact.Zbe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity manager built on the LibGDX Ashley entity management system. Each {@link com.squidtopusstudios.zerobitengine.refact.World} has it's own instance.
 */
public class EntityManager {
    
    private Engine ashley;
    /** ZbeEntity Maps arranged by user defined type **/
    private Map<String, List<ZbeEntity>> entities = new HashMap<String, List<ZbeEntity>>();


    public EntityManager() {
        ashley = new Engine();
    }

    public void update(float delta) {
        ashley.update(delta);
    }

    /**
     * Adds an entity to the world grouped by type
     * @param entity ZbeEntity instance to add
     * @param type type to group this entity under. E.g. wall
     */
    public void addEntity(ZbeEntity entity, String type) {
        Zbe.logger.logDebug("Adding entity: " + type);
        if (!entities.containsKey(type)) {
            entities.put(type, new ArrayList<ZbeEntity>());
        }
        entities.get(type).add(entity);
        ashley.addEntity(entity);
    }

    /**
     * Gets a List of entities identified by the type used to add them
     * @param type type of entities to get
     * @return List of entities identified by 'type'
     */
    public List<ZbeEntity> getEntities(String type) {
        return entities.get(type);
    }

    /**
     * Gets the first entity of 'type' added to the World.
     * Useful if you have a single entity type such as 'player'
     * @param type type of entity to get (used in addEntity)
     * @return the first entity identified by 'type'
     */
    public ZbeEntity getEntity(String type) {
        return entities.get(type).get(0);
    }

    /**
     * Called when the parent World is disposed
     */
    public void dispose() {
        Zbe.logger.logDebug("Disposing entities");
        ashley.removeAllEntities();
    }

    /**
     * @param pause whether to pause all Ashley systems. False will resume paused systems
     */
    public void pauseSystems(boolean pause) {
        for (EntitySystem system : ashley.getSystems()) {
            system.setProcessing(pause);
        }
    }

    /**
     * @return Ashley Engine instance for adding systems
     */
    public Engine getEngine() {
        return ashley;
    }
}
