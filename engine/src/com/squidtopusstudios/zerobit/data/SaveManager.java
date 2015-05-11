package com.squidtopusstudios.zerobit.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

/**
 * Saves and Loads objects
 */
public class SaveManager {

    private static Json json = new Json();

    /**
     * Loads an Object of type T from a previously saved file
     * @param type type of the Object to load
     * @param localPath local path to the file
     * @param encoded whether the file is encoded
     * @return the loaded Object of type T
     */
    public <T> T load(Class<T> type, String localPath, boolean encoded) {
        FileHandle file = Gdx.files.internal(localPath);
        if (encoded) {
            return json.fromJson(type, Base64Coder.decodeString(file.readString()));
        }
        return json.fromJson(type, file.readString());
    }
    /**
     * Loads an Object of type T from a previously saved file. Assumes the file is not encoded
     * @param type type of the Object to load
     * @param localPath local path to the file
     * @return the loaded Object of type T
     */
    public <T> T load(Class<T> type, String localPath) {
        return load(type, localPath, false);
    }
}
