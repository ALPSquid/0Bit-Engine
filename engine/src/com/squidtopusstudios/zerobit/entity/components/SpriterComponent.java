package com.squidtopusstudios.zerobit.entity.components;

import com.brashmonkey.spriter.PlayerTweener;
import com.brashmonkey.spriter.SpriterException;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.util.spriter.LibGdxDrawer;
import com.squidtopusstudios.zerobit.util.spriter.LibGdxLoader;

/**
 * Holds Spriter data
 */
public class SpriterComponent extends ZBComponent {

    public PlayerTweener player;
    public LibGdxLoader loader;
    public LibGdxDrawer drawer;
    public float defaultWeight = 0.5f;


    // ---- Helper methods

    public void setAnim(int playerNum, String name, float weight) {
        try {
            if (playerNum == 0) player.getFirstPlayer().setAnimation(name);
            else player.getSecondPlayer().setAnimation(name);
            player.setWeight(weight);
        } catch (SpriterException ex) {
            if (name.equals("run")) setFirstAnim("idle");
            else ZeroBit.logger.logDebug("Entity does not have animation: "+name);
        }
    }

    public void setFirstAnim(String name, float weight) {
        setAnim(0, name, weight);
    }
    public void setFirstAnim(String name) {
        setFirstAnim(name, defaultWeight);
    }

    public void setSecondAnim(String name, float weight) {
        setAnim(1, name, weight);
    }
    public void setSecondAnim(String name) {
        setAnim(1, name, defaultWeight);
    }

    @Override
    public void reset() {

    }
}
