package com.squidtopusstudios.zerobit.tools.keymapper;

import com.badlogic.gdx.Input;
import com.squidtopusstudios.zerobit.input.controllers.mappings.XboxOne;
import com.squidtopusstudios.zerobit.input.keybindings.GameActions;

/**
 * Example of using the KeyMapper tool for creating default keymaps. GUI tool may come in the future.
 * Note that the 0bit Engine has (WIP) builtin functionality for editing, loading, saving and using keymaps, so this tool is purely for outside of the game.
 */
public class CliExample {

    public static void main(String[] args) {
        KeyMapper keyMapper = new KeyMapper();

        /** Keyboard & Mouse Mapping */
        // Left
        keyMapper.addKeyMapping(KeyMapper.MappingType.BUTTON, Input.Keys.A, GameActions.PLAYER_LEFT);
        keyMapper.addKeyMapping(KeyMapper.MappingType.BUTTON, Input.Keys.LEFT, GameActions.PLAYER_LEFT);
        // Right
        keyMapper.addKeyMapping(KeyMapper.MappingType.BUTTON, Input.Keys.D, GameActions.PLAYER_RIGHT);
        keyMapper.addKeyMapping(KeyMapper.MappingType.BUTTON, Input.Keys.RIGHT, GameActions.PLAYER_RIGHT);
        // Jump
        keyMapper.addKeyMapping(KeyMapper.MappingType.BUTTON, Input.Keys.SPACE, GameActions.PLAYER_JUMP);
        keyMapper.addKeyMapping(KeyMapper.MappingType.BUTTON, Input.Keys.W, GameActions.PLAYER_JUMP);
        keyMapper.addKeyMapping(KeyMapper.MappingType.BUTTON, Input.Keys.UP, GameActions.PLAYER_JUMP);
        // Attack (left mouse button)
        keyMapper.addKeyMapping(KeyMapper.MappingType.BUTTON, Input.Buttons.LEFT, GameActions.PLAYER_ATTACK_RANGED);

        /** Controller Mapping */
        // Left
        keyMapper.addControllerMapping(KeyMapper.MappingType.AXIS_NEG, XboxOne.AXIS_LEFT_X, GameActions.PLAYER_LEFT);
        // Right
        keyMapper.addControllerMapping(KeyMapper.MappingType.AXIS_POS, XboxOne.AXIS_LEFT_X, GameActions.PLAYER_RIGHT);
        // Jump
        keyMapper.addControllerMapping(KeyMapper.MappingType.BUTTON, XboxOne.BUTTON_A, GameActions.PLAYER_JUMP);
        // Favourites Menu (D-pad)
        keyMapper.addControllerMapping(KeyMapper.MappingType.POV, XboxOne.BUTTON_DPAD_LEFT.ordinal(), GameActions.FAVES_LEFT);
        keyMapper.addControllerMapping(KeyMapper.MappingType.POV, XboxOne.BUTTON_DPAD_RIGHT.ordinal(), GameActions.FAVES_RIGHT);
        // Axis Deadzone (keycode is ignored when using the deadzone type)
        keyMapper.addControllerMapping(KeyMapper.MappingType.DEADZONE, 0, 0.45f);

        /** View the maps */
        System.out.println(keyMapper.keyMap);
        System.out.println(keyMapper.controllerMap);

        /** Save the maps */
        // Key map
        keyMapper.saveKeyMap("cliexample_output/key_map.json", false);
        // Controller map
        keyMapper.saveControllerMap("cliexample_output/controller_map.json", false);
        // Maps can also be encoded
        keyMapper.saveKeyMap("cliexample_output/key_map_encoded.json", true);

        /** Load a map */
        keyMapper.keyMap.clear(); // Maps are automatically cleared on loading; this is just for demonstration purposes
        keyMapper.loadKeyMap("cliexample_output/key_map_encoded.json", true);
        System.out.println(keyMapper.keyMap);
    }
}
