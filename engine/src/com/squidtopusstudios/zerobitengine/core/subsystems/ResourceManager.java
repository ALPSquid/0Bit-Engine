package com.squidtopusstudios.zerobitengine.core.subsystems;


import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.squidtopusstudios.zerobitengine.utils.IManager;
import com.squidtopusstudios.zerobitengine.utils.Logger;

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
    public synchronized void registerResource(String ID, FileHandle fileHandle, Class<?> type) {
        load(ID, fileHandle.path(), type, null);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param filePath the file name (interpretation depends on LibGDX.AssetLoader)
     * @param type the type of the asset.
     */
    public synchronized <T> void registerResource(String ID, String filePath, Class<T> type) {
        load(ID, filePath, type, null);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the LibGDX.AssetManager.
     * @param filePath the file name (interpretation depends on LibGDX.AssetLoader)
     * @param type the type of the asset.
     * @param parameters parameters for the LibGDX.AssetLoader.
     */
    public synchronized <T> void load(String ID, String filePath, Class<T> type, AssetLoaderParameters<T> parameters) {
        Logger.getInstance().logDebug("Loading " + type.toString() + ": " + filePath);
        registeredResources.put(ID, filePath);
        assetManager.load(filePath, type, parameters);
    }

    /**
     * From LibGDX:
     * Adds the given asset to the loading queue of the AssetManager.
     * @param desc the AssetDescriptor
     */
    public synchronized void registerResource(String ID, AssetDescriptor desc) {
        load(ID, desc.fileName, desc.type, desc.params);
    }

    public Class<?> getResource(String ID) {
        if (registeredResources.containsKey(ID)) {
            return assetManager.get(registeredResources.get(ID));
        } else {
            Logger.getInstance().logError("Resource not registered", new NullPointerException());
            return null;
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void dispose() {
        Logger.getInstance().logDebug("Disposing");
        assetManager.dispose();
        registeredResources.clear();
    }
}
