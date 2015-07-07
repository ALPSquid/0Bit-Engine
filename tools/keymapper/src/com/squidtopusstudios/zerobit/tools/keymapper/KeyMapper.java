package com.squidtopusstudios.zerobit.tools.keymapper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Tool for creating default key and controller maps.
 */
public class KeyMapper {

    public enum MappingType {
        BUTTON, AXIS_POS, AXIS_NEG, POV, DEADZONE
    }

    public Map<String, Integer> actions = new LinkedHashMap<String, Integer>();
    public Map<String, Float> keyMap = new LinkedHashMap<String, Float>();
    public Map<String, Float> controllerMap = new LinkedHashMap<String, Float>();
    private Json json = new Json();


    public KeyMapper() {
        json.setOutputType(JsonWriter.OutputType.json);
    }

    /**
     * Adds a mapping to the key or controller map. If DEADZONE type is used, keycode is ignored and action should be the deadzone value.
     * @param type Type of mapping. Prefixes and suffixes are added depending on this to avoid keycode collisions.
     * @param keyCode Integer code of the mapped button/axis. Ignored if DEADZONE is used as the type.
     * @param action Game action code to map to the button.
     * @param controller Whether to write to the controller map.
     */
    public void addMapping(MappingType type, int keyCode, float action, boolean controller) {
        String prefix = "";
        String suffix = "";
        switch (type) {
            case BUTTON: prefix = "btn-"; break;
            case AXIS_POS: prefix = "axis-"; suffix = "+"; break;
            case AXIS_NEG: prefix = "axis-"; suffix = "-"; break;
            case POV: prefix = "pov-"; break;
            case DEADZONE: prefix = "deadzone"; break;
        }
        ((controller)? controllerMap : keyMap).put(prefix + ((type.equals(MappingType.DEADZONE)) ? "" : keyCode) + suffix, action);
    }
    /**
     * Adds a mapping to the keymap. See {@link #addMapping(MappingType, int, float, boolean)}
     */
    public void addKeyMapping(MappingType type, int keyCode, float action) {
        addMapping(type, keyCode, action, false);
    }
    /**
     * Adds a mapping to the controller map. See {@link #addMapping(MappingType, int, float, boolean)}
     */
    public void addControllerMapping(MappingType type, int keyCode, float action) {
        addMapping(type, keyCode, action, true);
    }

    /**
     * Saves a key or controller map to a json file with optional Base64 encoding.
     * @param path Path to output file.
     * @param controller Whether to save the controller map.
     * @param encoded Whether to encode the file.
     */
    public void saveMap(String path, boolean controller, boolean encoded) {
        FileHandle file = new FileHandle(path);
        String output = json.prettyPrint((controller)? controllerMap : keyMap);
        if (encoded) output = Base64Coder.encodeString(output);
        file.writeString(output, false);
        System.out.println("Map saved to " + path);
    }
    /**
     * Saves the key map to a json file with optional Base64 encoding. See {@link #saveMap(String, boolean, boolean)}
     */
    public void saveKeyMap(String path, boolean encoded) {
        saveMap(path, false, encoded);
    }
    /**
     * Saves the controller map to a json file with optional Base64 encoding. See {@link #saveMap(String, boolean, boolean)}
     */
    public void saveControllerMap(String path, boolean encoded) {
        saveMap(path, true, encoded);
    }

    /**
     * Loads a key or controller map from a previously saved file.
     * @param path Path to map file.
     * @param controller Whether to load a controller map.
     * @param encoded Whether the file is encoded.
     */
    @SuppressWarnings("unchecked")
    public void loadMap(String path, boolean controller, boolean encoded) {
        FileHandle file = new FileHandle(path);
        Map<String, Float> map = json.fromJson(HashMap.class, (encoded)? Base64Coder.decodeString(file.readString()) : file.readString());
        ((controller) ? controllerMap : keyMap).clear();
        ((controller) ? controllerMap : keyMap).putAll(map);
    }
    /**
     * load a key map from a previously saved file. See {@link #loadMap(String, boolean, boolean)}
     */
    public void loadKeyMap(String path, boolean encoded) {
        loadMap(path, false, encoded);
    }
    /**
     * loads a controller map from a previously saved file. See {@link #loadMap(String, boolean, boolean)}
     */
    public void loadControllerMap(String path, boolean encoded) {
        loadMap(path, true, encoded);
    }

    /**
     * TODO: Part of GUI tool
     * Load an action map text file into the actions HashMap.
     * Action file should be in the format:
     *     action_name: action_value
     * with each action on its own line. Spaces in the name are allowed.
     * @param path Path to action file
     * @throws IOException
     */
    public void loadActionMap(String path) throws IOException {
        FileHandle file = new FileHandle(path);
        BufferedReader reader = file.reader(4096, "utf-8");
        String line;
        while ((line = reader.readLine()) != null) {
            String keyName = line.split(":")[0];
            int action = Integer.parseInt(line.split(":")[1].replace(" ", ""));
            actions.put(keyName, action);
        }
    }
}
