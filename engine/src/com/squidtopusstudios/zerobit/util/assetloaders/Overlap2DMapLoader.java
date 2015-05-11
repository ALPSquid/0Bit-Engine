package com.squidtopusstudios.zerobit.util.assetloaders;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.squidtopusstudios.zerobit.entity.EntityManager;
import com.squidtopusstudios.zerobit.util.assetloaders.settings.Overlap2DMapSettings;
import com.squidtopusstudios.zerobit.util.loaders.Overlap2DLoader;

/**
 * Loads and Overlap2D Scene to a {@link com.squidtopusstudios.zerobit.worlds.ZBWorld} and returns a setting file.<br/>
 * Paths should be given as internal strings. The TextureAtlas will be loaded if it hasn't already.
 */
public class Overlap2DMapLoader extends AsynchronousAssetLoader<Overlap2DMapSettings, Overlap2DMapLoader.Parameters> {
    // TODO: Create and document example loader tutorial including linking to worlds and views

    /** Paths should be given as internal strings. The TextureAtlas will be loaded if it hasn't already.*/
    public static class Parameters extends AssetLoaderParameters<Overlap2DMapSettings> {
        String projectPath;
        String scenePath;
        String atlas;
        EntityManager entityManager;

        public void set(String projectPath, String scenePath, String atlas, EntityManager entityManager) {
            this.projectPath = projectPath;
            this.scenePath = scenePath;
            this.atlas = atlas;
            this.entityManager = entityManager;
        }
    }

    Overlap2DMapSettings settings;

    public Overlap2DMapLoader() {
        super(new InternalFileHandleResolver());
    }
    public Overlap2DMapLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, Parameters parameter) {
        settings = new Overlap2DMapSettings();
        Overlap2DLoader.loadMap(resolve(parameter.projectPath), resolve(parameter.scenePath), manager.get(parameter.atlas, TextureAtlas.class), parameter.entityManager);
    }

    @Override
    public Overlap2DMapSettings loadSync(AssetManager manager, String fileName, FileHandle file, Parameters parameter) {
        Overlap2DMapSettings settings = this.settings;
        this.settings = null;
        return settings;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, Parameters parameter) {
        Array<AssetDescriptor> dependencies = new Array<AssetDescriptor>();
        dependencies.add(new AssetDescriptor(parameter.atlas, TextureAtlas.class));
        return dependencies;
    }
}
