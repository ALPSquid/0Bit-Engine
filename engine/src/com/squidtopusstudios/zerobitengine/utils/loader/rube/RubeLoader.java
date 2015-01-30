package com.squidtopusstudios.zerobitengine.utils.loader.rube;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.gushikustudios.rube.RubeScene;
import com.gushikustudios.rube.loader.RubeSceneLoader;
import com.gushikustudios.rube.loader.serializers.utils.RubeImage;
import com.squidtopusstudios.zerobitengine.ZeroBit;
import com.squidtopusstudios.zerobitengine.entity.ZbeEntityB2D;

import java.util.Map;

/**
 * Loader for RUBE scenes, uses https://github.com/tescott/RubeLoader
 */
public class RubeLoader /*extends RubeSceneLoader*/ {


    //@Override
    public static RubeScene loadScene(FileHandle file) {
        //ZeroBit.managers.resourceManager().setLoader(RubeScene.class, new RubeSceneAsyncLoader(ZeroBit.getWorldB2D().getB2DWorld(), new InternalFileHandleResolver()));
        //ZeroBit.managers.resourceManager().addResource("RubeScene", file, RubeScene.class);
        /*String scenePath = file.path().substring(0, file.path().lastIndexOf("/"));
        RubeSceneLoader loader = new RubeSceneLoader(ZeroBit.getWorldB2D().getB2DWorld());
        RubeScene scene = loader.loadScene(file);

        // Experimentation code
        for (Body body : scene.getBodies()) {
            for (Map.Entry<String, Object> entry : scene.getCustomPropertiesForItem(body, true).entrySet()) {
                // TODO add bodies of same EntityName to a new ZbeEntityB2D
                System.out.println(entry.getKey() + ": "+ entry.getValue());
            }
            ZbeEntityB2D entity = ZeroBit.managers.entityManager().createEntityB2D("test").addBody("test", body);
            //TODO Support multiple images
            if (scene.getMappedImage(body) != null) {
                RubeImage bodyImage = scene.getMappedImage(body).get(0);
                ZeroBit.managers.resourceManager().addResource("test", scenePath+"/"+bodyImage.file, ZeroBit.ResourceType.TEXTURE).loadAll();
                Texture texture = ZeroBit.managers.resourceManager().getResource("test");
                entity.setTexture(texture);
                entity.setBounds(bodyImage.width, bodyImage.height);
            }
            else {
                try {
                    String textureName = (String) scene.getCustom(body.getFixtureList().get(0), "TextureMask", null);
                    if (textureName != null) {
                        ZeroBit.managers.resourceManager().addResource("test", scenePath + "/" + textureName, ZeroBit.ResourceType.TEXTURE).loadAll();
                        Texture texture = ZeroBit.managers.resourceManager().getResource("test");
                        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

                        if (body.getFixtureList().get(0).getType() == Shape.Type.Polygon) {
                            PolygonShape poly = (PolygonShape)body.getFixtureList().get(0).getShape();
                            int vertexCount = poly.getVertexCount();
                            float[] vertices = new float[vertexCount * 2];
                            Vector2 temp = new Vector2();

                            for (int i=0; i < vertexCount; i++) {
                                poly.getVertex(i, temp);
                                temp.rotate(body.getAngle());
                                temp.add(body.getPosition());
                                vertices[i * 2] = ZeroBit.getWorldB2D().unitsToPixels(temp.x);
                                vertices[i * 2 + 1] = ZeroBit.getWorldB2D().unitsToPixels(temp.y);
                            }
                            EarClippingTriangulator ect = new EarClippingTriangulator();
                            short[] triangleIndices = ect.computeTriangles(vertices).toArray();
                            PolygonRegion polyRegion = new PolygonRegion(new TextureRegion(texture), vertices, triangleIndices);
                            entity.polygonRegion = polyRegion;
                        }
                    }
                } catch(IndexOutOfBoundsException ex) {}
            }
        }
        for (RubeImage image : scene.getImages()) {
            if (image.body == null) {
                ZbeEntityB2D entity = ZeroBit.managers.entityManager().createEntityB2D("test");
                entity.createBody("test", new BodyDef());
                entity.setPosition(image.center.x, image.center.y);
                ZeroBit.managers.resourceManager().addResource("test", scenePath+"/"+image.file, ZeroBit.ResourceType.TEXTURE).loadAll();
                Texture texture = ZeroBit.managers.resourceManager().getResource("test");
                entity.setTexture(texture);
                entity.setBounds(image.width, image.height);
            }
        }*/
        //return scene;
        return null;
    }
}
