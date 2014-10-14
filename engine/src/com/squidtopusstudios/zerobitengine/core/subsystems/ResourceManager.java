package com.squidtopusstudios.zerobitengine.core.subsystems;


import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.squidtopusstudios.zerobitengine.core.ZeroBit;
import com.squidtopusstudios.zerobitengine.utils.IManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Main resource manager for the engine. Handles registering, retrieving and disposing of physical resources.
 * Basically a wrapper/handler for the LibGDX built in AssetManager
 */
public class ResourceManager implements IManager {

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
     * A simpler way to add a resource
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param fileHandle the filehandle from Gdx.files.X
     * @param type the type of the asset.
     */
    public synchronized void addResource(String ID, FileHandle fileHandle, Class<?> type) {
        load(ID, fileHandle.path(), type, null);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param filePath the file name (interpretation depends on LibGDX.AssetLoader)
     * @param type the type of the asset.
     */
    public synchronized <T> void addResource(String ID, String filePath, Class<T> type) {
        load(ID, filePath, type, null);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param filePath the file name (interpretation depends on LibGDX.AssetLoader)
     * @param type the type of the asset.
     */
    public synchronized <T> void addResource(String ID, String filePath, Class<T> type, AssetLoaderParameters<T> parameters) {
        load(ID, filePath, type, parameters);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param filePath the file name (interpretation depends on LibGDX.AssetLoader)
     * @param type the type of the asset.
     * @param parameters parameters for the LibGDX.AssetLoader.
     */
    private synchronized <T> void load(String ID, String filePath, Class<T> type, AssetLoaderParameters<T> parameters) {
        ZeroBit.logger.logDebug("Loading " + type.getName() + ": " + filePath);
        registeredResources.put(ID, filePath);
        assetManager.load(filePath, type, parameters);
        if (autoLoad) {
            loadAll();
        }
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

    /**
     * Loads all queued resources - blocking
     */
    public void loadAll() {
        assetManager.finishLoading();
    }

    /**
     * Sets auto loading of resources on or off
     * @param on whether autoLoad should be set to on
     */
    public void autoLoad(boolean on) {
        autoLoad = on;
    }

    @Override
    public void update() {
        assetManager.update();
    }

    @Override
    public void dispose() {
        ZeroBit.logger.logDebug("Disposing");
        assetManager.dispose();
        registeredResources.clear();
    }
}
