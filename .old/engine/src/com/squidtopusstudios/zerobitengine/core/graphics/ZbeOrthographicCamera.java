package com.squidtopusstudios.zerobitengine.core.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;

/**
 * Extended functionality on the LibGDX Orthographic camera
 */
public class ZbeOrthographicCamera extends OrthographicCamera {

    protected ZbeEntityBase followEntity;
    protected boolean limitFollow;
    protected float minX;
    protected float maxX;
    protected float minY;
    protected float maxY;
    protected float followX;
    protected float followY;
    protected float followZ = 0;


    public boolean isFollowing() {
        return followEntity != null;
    }

    @Override
    public void update() {
        super.update();
        if (followEntity != null) {
            followX = followEntity.getX();
            followY = followEntity.getY();
            if (followEntity.isBox2D()) {
                followX *= (float)ZeroBit.getWorld().getPixelsPerUnit();
                followY *= (float)ZeroBit.getWorld().getPixelsPerUnit();
            }
            if (limitFollow) {
                if (followX < minX) {
                    followX = minX;
                } else if (followX > maxX) {
                    followX = maxX;
                }
                if (followY < minY) {
                    followY = minY;
                } else if (followY > maxY) {
                    followY = maxY;
                }
            }
            position.set(followX, followY, followZ);
        }
    }

    /**
     * Set the camera to follow the target {@link ZbeEntityBase}
     * @param entity {@link ZbeEntityBase} to follow
     * @param minX minimum X position of the camera from the left edge
     * @param maxX maximum X position of the camera from the right edge
     * @param minY minimum Y position of the camera from the bottom edge
     * @param maxY maximum Y position of the camera from the top edge
     * @param limitFollow whether to limit the cameras x and y while following, only there to allow for simpler follow methods
     */
    private void follow(ZbeEntityBase entity, float minX, float maxX, float minY, float maxY, boolean limitFollow) {
        followEntity = entity;
        this.minX = minX + viewportWidth/2;
        this.maxX = maxX - viewportWidth/2;
        this.minY = minY + viewportHeight/2;
        this.maxY = maxY - viewportHeight/2;
        this.limitFollow = limitFollow;
    }

    /**
     * Set the camera to follow the target {@link ZbeEntity}
     * @param entity {@link ZbeEntity} to follow
     * @param minX minimum X position of the camera from the left edge
     * @param maxX maximum X position of the camera from the right edge
     * @param minY minimum Y position of the camera from the bottom edge
     * @param maxY maximum Y position of the camera from the top edge
     */
    public void follow(ZbeEntityBase entity, float minX, float maxX, float minY, float maxY) {
        follow(entity, minX, maxX, minY, maxY, true);
    }

    /**
     * Set the camera to follow the target {@link ZbeEntity} without x and y limits
     * @param entity {@link ZbeEntity} to follow
     */
    public void follow(ZbeEntityBase entity) {
        follow(entity, 0, 0, 0, 0, false);
    }

    /** Stop following **/
    public void stopFollow() {
        followEntity = null;
    }
}
