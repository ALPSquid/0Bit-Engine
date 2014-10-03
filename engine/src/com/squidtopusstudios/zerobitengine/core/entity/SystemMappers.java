package com.squidtopusstudios.zerobitengine.core.entity;

import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.systems.MetaSystem;
import com.squidtopusstudios.zerobitengine.core.entity.systems.SpriteSystem;

/**
 * Manages Ashley systems
 */
public class SystemMappers {

    public static MetaSystem metaSystem() {
        return ZeroBit.managers.entityManager().getSystem(MetaSystem.class);
    }

    public static SpriteSystem spriteSystem() {
        return ZeroBit.managers.entityManager().getSystem(SpriteSystem.class);
    }
}
