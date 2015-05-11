package com.squidtopusstudios.zerobit.levelsDEL;

import com.squidtopusstudios.zerobit.worlds.ZBWorld;


/**
 * ExampleLevel. TODO: Refactor into external loader example
 */
public class Example extends ZBWorld {

    public Example() {
        super();
    }


    @Override
    public void load(final Runnable callBack) {
        // Level
        /*Overlap2DMapLoader.Parameters params = new Overlap2DMapLoader.Parameters();
        params.set(Assets.overlap2DLevelProject, Assets.lvlTradingArea, Assets.overlap2DLevelAtlas, getEntities());
        params.loadedCallback = new AssetLoaderParameters.LoadedCallback() {
            @Override
            public void finishedLoading(AssetManager assetManager, String fileName, Class type) {
                getEntities().getEngine().getSystem(InputSystem.class).setControllable(getEntities().getEntity(WKWorld.PLAYER_IDENTIFIER));
                getEntities().getEngine().getSystem(CameraSystem.class).follow(getEntities().getEntity(WKWorld.PLAYER_IDENTIFIER),
                        getEntities().getEntity(WKWorld.GROUND_IDENTIFIER).getComponent(Box2DComponent.class).body.getPosition().x,
                        getEntities().getEntity("border_right").getComponent(Box2DComponent.class).body.getPosition().x,
                        0f, null);
                getEntities().getEngine().getSystem(DaySystem.class).setSkyBoxes(getEntities().getEntity(WKWorld.SKYBOX_DAY_IDENTIFIER),
                                                                                 getEntities().getEntity(WKWorld.SKYBOX_NIGHT_IDENTIFIER));

                if (callBack != null) callBack.run();
            }
        };

        Assets.manager.load(Assets.lvlTradingAreaID, Overlap2DMapSettings.class, params);*/
    }
}
