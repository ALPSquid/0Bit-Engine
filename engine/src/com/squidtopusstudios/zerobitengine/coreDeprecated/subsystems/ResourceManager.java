package com.squidtopusstudios.zerobitengine.coreDeprecated.subsystems;


import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.squidtopusstudios.zerobitengine.ZeroBit;

import java.util.HashMap;
import java.util.Map;

/**
 * Main resource manager for the engine. Handles registering, retrieving and disposing of physical resources.
 * Basically a wrapper/handler for the LibGDX built in AssetManager
 */
public class ResourceManager {

    private static ResourceManager resourceManagerInstance;
    private AssetManager assetManager = new AssetManager();
    private Map<String, String> registeredResources = new HashMap<String, String>();
    private boolean autoLoad = false;


    private ResourceManager() {}

    public static ResourceManager getInstance() {
        if (resourceManagerInstance == null) {
            resourceManagerInstance = new ResourceManager();
        }
        return resourceManagerInstance;
    }

    /**
     * Allows adding a resource using a FileHandle (e.g. gdx.files.internal)
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param fileHandle the filehandle from Gdx.files.X
     * @param type the type of the asset. From ZeroBit.ResourceType.TYPE
     */
    public synchronized ResourceManager addResource(String ID, FileHandle fileHandle, Class<?> type) {
        return load(ID, fileHandle.path(), type, null);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param filePath the file name (interpretation depends on LibGDX.AssetLoader)
     * @param type the type of the asset.
     */
    public synchronized <T> ResourceManager addResource(String ID, String filePath, Class<T> type) {
        return load(ID, filePath, type, null);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param filePath the file name (interpretation depends on LibGDX.AssetLoader)
     * @param type the type of the asset. From ZeroBit.ResourceType.TYPE
     * @param parameters parameters for the LibGDX.AssetLoader.
     */
    public synchronized <T> ResourceManager addResource(String ID, String filePath, Class<T> type, AssetLoaderParameters<T> parameters) {
        return load(ID, filePath, type, parameters);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param filePath the file name (interpretation depends on LibGDX.AssetLoader)
     * @param type the type of the asset. From ZeroBit.ResourceType.TYPE
     * @param parameters parameters for the LibGDX.AssetLoader.
     */
    private synchronized <T> ResourceManager load(String ID, String filePath, Class<T> type, AssetLoaderParameters<T> parameters) {
        ZeroBit.logger.logDebug("Loading " + type.getName() + ": " + filePath);
        registeredResources.put(ID, filePath);
        assetManager.load(filePath, type, parameters);
        if (autoLoad) {
            loadAll();
        }
        return this;
    }

    /**
     * Unloads the target resource from AssetManager
     * @param ID ID of the target resource
     */
    public synchronized void removeResource(String ID) {
        if (registeredResources.containsKey(ID)) {
            assetManager.unload(registeredResources.get(ID));
            registeredResources.remove(ID);
        } else {
            ZeroBit.logger.logError("Attempted to access unregistered resource: "+ID+", use registerResource()",
                    new NullPointerException());
        }
    }

    /**
     * Automatic type casting resource getter
     * @param ID ID of the resource
     * @param <T> Type of the resource
     * @return The requested resource
     */
    public synchronized <T> T getResource(String ID) {
        if (registeredResources.containsKey(ID)) {
            return assetManager.get(registeredResources.get(ID));
        } else {
            ZeroBit.logger.logError("Attempted to access unregistered resource: "+ID+", use registerResource()",
                    new NullPointerException());
            return null;
        }
    }

    /** Sets a new {@link AssetLoader} for the given type.
     * @param type the type of the asset
     * @param loader the loader */
    public synchronized <T, P extends AssetLoaderParameters<T>> void setLoader (Class<T> type, AssetLoader<T, P> loader) {
        setLoader(type, null, loader);
    }

    /** Sets a new {@link AssetLoader} for the given type.
     * @param type the type of the asset
     * @param suffix the suffix the filename must have for this loader to be used or null to specify the default loader.
     * @param loader the loader */
    public synchronized <T, P extends AssetLoaderParameters<T>> void setLoader (Class<T> type, String suffix,
                                                                                AssetLoader<T, P> loader) {
        assetManager.setLoader(type, suffix, loader);
    }

    /**
     * Loads all queued resources - blocking
     */
    public void loadAll() {
        assetManager.finishLoading();
    }

    /**
     * Sets auto loading of resources on or off
     * @param autoLoad whether autoLoad should be set to on
     */
    public void autoLoad(boolean autoLoad) {
        this.autoLoad = autoLoad;
    }

    public void update() {
        assetManager.update();
    }

    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
        assetManager.dispose();
        registeredResources.clear();
    }
}
