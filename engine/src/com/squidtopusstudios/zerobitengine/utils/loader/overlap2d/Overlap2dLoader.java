package com.squidtopusstudios.zerobitengine.utils.loader.overlap2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntity;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityB2D;
import com.squidtopusstudios.zerobitengine.core.entity.ZbeEntityBase;
import com.uwsoft.editor.renderer.data.MainItemVO;
import com.uwsoft.editor.renderer.data.MeshVO;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.data.SceneVO;

import java.util.*;

/**
 * Level loader for Overlap2D: http://overlap2d.com/
 * Based on https://github.com/UnderwaterApps/overlap2d-runtime-libgdx
 */
public class Overlap2dLoader {

    public static Map<String, Class> classMap = new HashMap<String, Class>();
    static {
        classMap.put("entity", ZbeEntity.class);
    }

    /*
     * PLAN
     *  Load the requested scene. Each Entity is set to a new ZbeEntity and added to the current World instance.
     *  Each entity's z-index property is updated accordingly and the render manager renders them according
     *  to this. Z-index should just be the order in which the entities are loaded from the JSON source.
     *  Support for polygon collisions from Overlap2D physics data?
     *
     *  Also able to load a requested scene as a Stage for menu screens and UI which returns the stage
     *
     *  Use the Overlap2D libGDX runtime classes to do most of the object loading
     */


    /**
     * Loads the specified scene into the World. Entities within the scene are automatically added to the current
     * {@link com.squidtopusstudios.zerobitengine.World}'s entity list and can be accessed, as normal, using {@link com.squidtopusstudios.zerobitengine.World}.entities()
     * @param scenePath path to scene.dt to load
     * @param projectPath path to project.dt to load
     * @param atlas TextureAtlas used by the scene
     */
    public static void loadMap(FileHandle projectPath, FileHandle scenePath, TextureAtlas atlas) {
        ZeroBit.logger.logDebug("Loading Overlap2D scene: " + scenePath.toString());
        Json json = new Json();
        SceneVO sceneVO = json.fromJson(SceneVO.class, scenePath.readString());
        ProjectInfoVO projectVO = json.fromJson(ProjectInfoVO.class, projectPath.readString());

        for (MainItemVO item : sceneVO.composite.getAllItems()) {
            // Create an entity, needs null checks and further properties
            TextureRegion tr = atlas.findRegion(item.imageName);
            // Create the entity from the item properties
            // TODO Box2D/Standard option in editor
            ZbeEntityB2D entity = new ZbeEntityB2D(item.itemIdentifier);
            entity.setWorld(ZeroBit.getWorld());
            entity.createBody("base", new BodyDef());
            entity.setPosition(ZeroBit.getWorld().pixelsToUnits(item.x), ZeroBit.getWorld().pixelsToUnits(item.y))
                    .setBounds(ZeroBit.getWorld().pixelsToUnits(tr.getRegionWidth()*item.scaleX), ZeroBit.getWorld().pixelsToUnits(tr.getRegionHeight()*item.scaleY))
                    .setTextureRegion(tr);
            try {
                // Load the mesh data and add to a list of point arrays
                MeshVO meshData = projectVO.meshes.get(item.meshId+"");
                List<float[]> polygonPoints = new ArrayList<float[]>();
                for (int i=0; i < meshData.minPolygonData.length; i++) {
                    float[] verts = new float[meshData.minPolygonData[i].length * 2];
                    for (int j=0; j < verts.length; j+=2) {
                        verts[j] = ZeroBit.getWorld().pixelsToUnits(meshData.minPolygonData[i][j/2].x); // points are offset for some reason
                        verts[j+1] = ZeroBit.getWorld().pixelsToUnits(meshData.minPolygonData[i][j/2].y); // points are offset for some reason
                        // TODO: change x & y to be the difference from item x & y and update in setPosition
                    }
                    polygonPoints.add(verts);
                    PolygonShape poly = new PolygonShape();
                    poly.set(verts);
                    entity.createFixture("base", "base"+i, poly, entity.getDefaultDensity());
                    poly.dispose();
                }
                // Set the entity's complex bounds to the points in the list
                // TODO create Body
                //((ZbeEntityB2D) entity).asPolygon(polygonPoints.toArray(new float[polygonPoints.size()][]));
            } catch (NullPointerException ex) {
                // nobody cares
            }
            // Add the entity to the world
            ZeroBit.getWorld().entities().addEntity(entity);
        }
    }

    /**
     * Loads the specified scene as a LibGDX Stage. Entities within the scene are automatically added to a new Stage
     * instance and can be accessed through it
     * @param scenePath path of scene.dt to load
     * @param projectPath path to project.dt to load
     * @param resolution Overlap2D resolution option to load
     * @param atlas TextureAtlas used by the scene
     * @return new Stage instance
     */
    public static Stage loadStage(FileHandle projectPath, FileHandle scenePath, String resolution, TextureAtlas atlas) {
        return null;
    }
}
