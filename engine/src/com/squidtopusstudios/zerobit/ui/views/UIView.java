package com.squidtopusstudios.zerobit.ui.views;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.squidtopusstudios.zerobit.ui.controllers.UIController;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract View class for UIs
 */
public abstract class UIView extends View {

    /** External loaders should populate this map with actors for the view to request TODO: External Loaders Docs */
    protected Map<String, Actor> actors;
    protected Stage stage;
    private boolean visible = true;


    public UIView(UIController controller) {
        stage = new Stage();
        actors = new HashMap<String, Actor>();
        controller.addInputProcessor(stage);
        loadUI(); // TODO: Remove when universal external Loader support added
    }

    /**
     * TODO: Universal external Loader support
     * @param scenePath path to Overlap2D scene
     * @param sceneID AssetManager ID for the loaded scene
     */
    /*public UIView(UIController controller, String scenePath, String sceneID) {
        this(controller);
        createUI(scenePath, sceneID);
    }*/

    public void createUI(String scenePath, String sceneID) {
        // TODO: Universal external Loader support
        /*Overlap2DUILoader.Parameters params = new Overlap2DUILoader.Parameters();
        params.set(Assets.overlap2DUIProject, scenePath,  Assets.overlap2DUIAtlas, Assets.overlap2DUIFonts, stage, actors);
        params.loadedCallback = new AssetLoaderParameters.LoadedCallback() {
            @Override
            public void finishedLoading(AssetManager assetManager, String fileName, Class type) {
                loadUI();
            }
        };
        Assets.manager.load(sceneID, Overlap2DUISettings.class, params);*/
    };

    /**
     * Load your UI here
     */
    public abstract void loadUI();

    /**
     * Should be called each frame from the {@link UIController}. Make sure you call super.render()!
     */
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        if (stage != null) stage.dispose();
    }


    /**
     * Sets visible property for all actors on the stage
     */
    public void setVisible(boolean visible) {
        for (Actor actor : stage.getActors()) {
            actor.setVisible(visible);
        }
        this.visible = visible;
    }

    /**
     * @return Whether the stage is visible
     */
    public boolean isVisible() {
        return visible;
    }
}
