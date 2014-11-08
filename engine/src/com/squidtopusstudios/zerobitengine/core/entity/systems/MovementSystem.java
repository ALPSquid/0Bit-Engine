package com.squidtopusstudios.zerobitengine.core.entity.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ComponentMappers;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityB2D;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;
import com.squidtopusstudios.zerobitengine.core.entity.components.CollisionComponent;
import com.squidtopusstudios.zerobitengine.utils.Utils;

/**
 * handles movement for Ashley Entities
 */
public class MovementSystem extends ZbeSystem {

    private ZbeEntity solidEntity;

    @Override
    public void processEntity(ZbeEntityBase entity, float deltaTime) {
        if (!entity.isBox2D()) {
            ZbeEntity zbeEntity = (ZbeEntity)entity; // Fun Fact: Doing this instead of downcasting the following use cases
                                                     // saves 0.4 seconds every hour at 60 ticks/s. It's also a bit easier to manage...
            ComponentMappers.position.get(entity).prevPosition = entity.getPosition();
            if (ComponentMappers.physics.get(entity).applyGravity) {
                zbeEntity.increaseVelocity(0, -ZeroBit.getWorld().getGravity() * deltaTime);
            }
            moveBy(zbeEntity, zbeEntity.getVelocity().x, zbeEntity.getVelocity().y, true);
        } else {
            ZbeEntityB2D zbeEntityB2D = (ZbeEntityB2D)entity;
            switch (zbeEntityB2D.getBoundsOrigin()) {
                case CENTER:
                    zbeEntityB2D.setBoundsPosition((zbeEntityB2D.getBoundsAnchor().getPosition().x + zbeEntityB2D.getBoundsOffset().x) - ZeroBit.getWorld().pixelsToUnits(zbeEntityB2D.getWidth()/2),
                                                   (zbeEntityB2D.getBoundsAnchor().getPosition().y + zbeEntityB2D.getBoundsOffset().y) - ZeroBit.getWorld().pixelsToUnits(zbeEntityB2D.getHeight()/2));
                    break;
                case BOTTOM_LEFT:
                    zbeEntityB2D.setBoundsPosition((zbeEntityB2D.getBoundsAnchor().getPosition().x + zbeEntityB2D.getBoundsOffset().x),
                                                    (zbeEntityB2D.getBoundsAnchor().getPosition().y + zbeEntityB2D.getBoundsOffset().y));
            }

            /*if (zbeEntityB2D.getType().equals("")) {
                // TODO Centering bounds on polygons and positioning bounds in center of polygons. Option to set center?
                if (zbeEntityB2D.getBody().getFixtureList().get(0).getShape().getType().equals(Shape.Type.Polygon)) {
                    PolygonShape poly = (PolygonShape) zbeEntityB2D.getBody().getFixtureList().get(0).getShape();
                    float[] verts = new float[poly.getVertexCount() * 2];
                    Vector2 tmp = new Vector2();
                    for (int i = 0; i < poly.getVertexCount(); i++) {
                        poly.getVertex(i, tmp);
                        verts[i] = tmp.x;
                        verts[i + 1] = tmp.y;
                    }
                    zbeEntityB2D.setBoundsPosition((zbeEntityB2D.getBody().getWorldCenter().x + zbeEntityB2D.getBoundsOffset().x) - ZeroBit.getWorld().pixelsToUnits(zbeEntityB2D.getWidth() / 2),
                            (zbeEntityB2D.getBody().getWorldCenter().y + zbeEntityB2D.getBoundsOffset().y) - ZeroBit.getWorld().pixelsToUnits(zbeEntityB2D.getHeight() / 2));
                }
            }*/
        }
    }

    /**
     * Moves the entity by x, y , checking for solidType collisions as it does so
     * @param entity the target entity to move
     * @param x number of units to move along the x axis
     * @param y number of units to move along the y axis
     * @param interpolate whether to interpolate the movement.
     *                    If true, x and y are considered to be the amount of movement in 1 second
     */
    public void moveBy(ZbeEntity entity, float x, float y, boolean interpolate) {
        x *= ZeroBit.getWorld().getPixelsPerUnit();
        y *= ZeroBit.getWorld().getPixelsPerUnit();

        if (interpolate) {
            x *= ZeroBit.getDelta();
            y *= ZeroBit.getDelta();
        }
        if (entity.collisionsEnabled()) {
            entity.getComponent(CollisionComponent.class).collidedRight = false;
            entity.getComponent(CollisionComponent.class).collidedLeft = false;
            entity.getComponent(CollisionComponent.class).collidedTop = false;
            entity.getComponent(CollisionComponent.class).collidedBottom = false;
            // Move x
            for (String solidType : entity.getSolidTypes()) {
                solidEntity = entity.collides(entity, solidType, entity.getX() + x, entity.getY());
                if (solidEntity != null) {
                    if ( x > 0) {
                        // Moving right
                        x = solidEntity.getX() - (entity.getX() + entity.getWidth());
                        entity.getComponent(CollisionComponent.class).collidedRight = true;
                    } else if (x < 0) {
                        // Moving left
                        x = (solidEntity.getX() + solidEntity.getWidth()) - entity.getX();
                        entity.getComponent(CollisionComponent.class).collidedLeft = true;
                    }
                    break;
                }
            }
            for (String solidType : entity.getSolidTypes()) {
                // move Y
                if (entity.getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
                    // fix rounding errors with a gravity bias
                    if (y == 0) {
                        y = -0.01f;
                    }
                }
                solidEntity = entity.collides(entity, solidType, entity.getX(), entity.getY() + y);
                if (solidEntity != null) {
                    if (y > 0) {
                        // Moving up
                        y = solidEntity.getY() - (entity.getY() + entity.getHeight());
                        entity.getComponent(CollisionComponent.class).collidedTop = true;

                        if (entity.getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
                            entity.setYVelocity(0);
                        }
                    } else if (y < 0) {
                        // Moving down
                        y = (solidEntity.getY() + solidEntity.getHeight()) - entity.getY();
                        entity.getComponent(CollisionComponent.class).collidedBottom = true;

                        if (entity.getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER)) {
                            entity.onGround = true;
                            entity.setYVelocity(0);
                        }
                    }
                    break;
                }
            }
            if (entity.getPhysicsType().equals(ZeroBit.PhysicsType.PLATFORMER) && !entity.isCollidingBottom()) {
                entity.onGround = false;
            }
        }
        entity.setPosition(entity.getX() + x, entity.getY() + y);
    }
}
