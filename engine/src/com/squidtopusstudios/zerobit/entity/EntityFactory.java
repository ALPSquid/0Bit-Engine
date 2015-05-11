package com.squidtopusstudios.zerobit.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.squidtopusstudios.zerobit.entity.ai.controllers.EnemyController;
import com.squidtopusstudios.zerobit.entity.ai.controllers.NPCController;
import com.squidtopusstudios.zerobit.entity.components.*;
import com.squidtopusstudios.zerobit.entity.components.ai.BehaviourComponent;
import com.squidtopusstudios.zerobit.entity.systems.Box2DSystem;
import com.squidtopusstudios.zerobit.util.loaders.MeshData;

import java.util.List;

/**
 * Creates Entities based on a file or given criteria
 */
public class EntityFactory {


    /**
     * Creates a rectangular Box2D Entity of width, height with a wheel as it's base
     * @param entityID ID of the entity
     * @param box2DSystem the {@link Box2DSystem} with the Box2D World to add the Body to
     * @param width full width of the body
     * @param height full height of the body
     * @param friction optional custom friction
     * @param density optional custom density
     * @param components List of additional Components to add to the entity. Use null for none
     * @return the created Entity
     */
    public static Entity createActor(String entityID, Box2DSystem box2DSystem, float width, float height, float friction, float density, List<Component> components) {
        float boxHeight = height - width/2;
        float boxOffset = width/2;
        if (density == -1) density = 0.6f;
        if (friction == -1) friction = 0.6f;

        Entity entity = new Entity();
        entity.add(new Box2DComponent());
        entity.add(new StateComponent());
        entity.add(new TransformComponent());
        entity.add(new MovementComponent());
        entity.add(new StatComponent());
        entity.add(new MessagingComponent());
        if (components != null) {
            for (Component component : components) {
                entity.add(component);
            }
        }

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.active = true;

        // Box
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2, boxHeight/2, new Vector2(0/*width/2*/, height/2 - width/2), 0);
        FixtureDef fixtureDefB = new FixtureDef();
        fixtureDefB.shape = poly;
        fixtureDefB.density = density;
        fixtureDefB.friction = 0;
        fixtureDefB.filter.maskBits = PhysicsFilters.MASK_ACTOR;
        fixtureDefB.filter.groupIndex = PhysicsFilters.GROUP_ACTORS;

        Body box = box2DSystem.getB2World().createBody(bodyDef);
        box.createFixture(fixtureDefB);
        box.setFixedRotation(true);

        // Wheel
        CircleShape circle = new CircleShape();
        circle.setRadius(width/2);
        circle.setPosition(new Vector2(0/*width/2*/, width/2));
        FixtureDef fixtureDefC = new FixtureDef();
        fixtureDefC.shape = circle;
        fixtureDefC.density = density;
        fixtureDefC.friction = friction;
        fixtureDefC.filter.maskBits = PhysicsFilters.MASK_ACTOR;
        fixtureDefC.filter.groupIndex = PhysicsFilters.GROUP_ACTORS;

        Body wheel = box2DSystem.getB2World().createBody(bodyDef);
        wheel.setUserData(new Box2DUserData());
        ((Box2DUserData)wheel.getUserData()).id = entityID;
        ((Box2DUserData)wheel.getUserData()).stateComponent = entity.getComponent(StateComponent.class);
        wheel.createFixture(fixtureDefC);
        wheel.setFixedRotation(true);

        // Joint
        RevoluteJointDef motor = new RevoluteJointDef();
        motor.bodyA = box;
        motor.bodyB = wheel;
        motor.collideConnected = false;
        motor.localAnchorA.set(0/*width/2*/, -boxOffset/2);
        motor.localAnchorB.set(circle.getPosition());
        box2DSystem.getB2World().createJoint(motor);

        // Foot Sensor
        poly.setAsBox(width/4, 0.1f, new Vector2(0/*width/2*/, 0), 0);
        FixtureDef fixtureDefS = new FixtureDef();
        fixtureDefS.shape = poly;
        fixtureDefS.isSensor = true;
        Fixture sensor = wheel.createFixture(fixtureDefS);
        sensor.setUserData(new Box2DUserData());
        ((Box2DUserData)sensor.getUserData()).id = Identifiers.FOOT_SENSOR;
        ((Box2DUserData)sensor.getUserData()).stateComponent = entity.getComponent(StateComponent.class);

        entity.getComponent(Box2DComponent.class).body = wheel;
        poly.dispose();
        circle.dispose();

        return entity;
    }
    public static Entity createActor(String entityID, Box2DSystem box2DSystem, float width, float height, List<Component> components) {
        return createActor(entityID, box2DSystem, width, height, -1, -1, components);
    }

    /**
     * Creates an actor with Player specific components and attributes. See {@link #createActor(String, com.squidtopusstudios.zerobit.entity.systems.Box2DSystem, float, float, List)}
     * @return the created Entity
     */
    public static Entity createPlayer(String entityID, Box2DSystem box2DSystem, float width, float height, List<Component> components) {
        Entity entity = createActor(entityID, box2DSystem, width, height, components);
        entity.add(new PlayerComponent());
        return entity;
    }

    /**
     * Creates an actor with NPC specific components and attributes. See {@link #createActor(String, com.squidtopusstudios.zerobit.entity.systems.Box2DSystem, float, float, List)}
     * @return the created Entity
     */
    public static Entity createNPC(String entityID, Box2DSystem box2DSystem, float width, float height, List<Component> components) {
        Entity entity = createActor(entityID, box2DSystem, width, height, components);
        entity.add(new BehaviourComponent());
        entity.getComponent(BehaviourComponent.class).controller = new NPCController(entity);
        return entity;
    }

    /**
     * Creates an actor with basic Enemy specific components and attributes. See {@link #createActor(String, com.squidtopusstudios.zerobit.entity.systems.Box2DSystem, float, float, List)}
     * @return the created Entity
     */
    public static Entity createEnemy(String entityID, Box2DSystem box2DSystem, float width, float height, List<Component> components, float patrolMinX, float patrolMaxX) {
        Entity entity = createActor(entityID, box2DSystem, width, height, 0.5f, 0.3f, components);
        entity.add(new BehaviourComponent());
        entity.getComponent(BehaviourComponent.class).controller = new EnemyController(entity, patrolMinX, patrolMaxX);
        return entity;
    }

    /**
     * Creates an new Entity based on the follow:
     * @param entityID ID of this entity
     * @param box2DSystem the {@link Box2DSystem} with the Box2D World to add the Body to
     * @param meshData {@link MeshData} object to load from
     * @param components List of Components to add to the entity
     * @return the created Entity
     */
    public static Entity createEntity(String entityID, Box2DSystem box2DSystem, MeshData meshData, List<Component> components) {
        Entity entity = new Entity();

        if (meshData != null) {
            if (entity.getComponent(Box2DComponent.class) == null) entity.add(new Box2DComponent());
            Body body = box2DSystem.getB2World().createBody(meshData.bodyDef);
            body.setMassData(meshData.massData);
            body.setUserData(new Box2DUserData());
            for (FixtureDef fixtureDef : meshData.fixtureDefs) {
                body.createFixture(fixtureDef);
            }
            ((Box2DUserData)body.getUserData()).id = entityID;
            entity.getComponent(Box2DComponent.class).body = body;
        }

        entity.add(new MessagingComponent());
        entity.add(new StateComponent());
        entity.add(new TransformComponent());
        if (components != null) {
            for (Component component : components) {
                entity.add(component);
            }
        }

        return entity;
    }
}
