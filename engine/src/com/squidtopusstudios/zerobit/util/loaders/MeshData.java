package com.squidtopusstudios.zerobit.util.loaders;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;

public class MeshData {
        public BodyDef bodyDef;
        public FixtureDef[] fixtureDefs;
        public MassData massData;

        public MeshData(BodyDef bodyDef, MassData massData, FixtureDef[] fixtureDefs) {
            this.bodyDef = bodyDef;
            this.massData = massData;
            this.fixtureDefs = fixtureDefs;
        }
    }