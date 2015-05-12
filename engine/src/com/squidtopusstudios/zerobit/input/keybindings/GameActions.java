package com.squidtopusstudios.zerobit.input.keybindings;

/**
 * Example Game actions for key mapping and event checking. Events dispatched by {@link com.squidtopusstudios.zerobit.input.InputMapper} correspond to the actions found in this class.
 */
public class GameActions {
    // TODO: Docs and a way to add new actions

    public static final int PLAYER_MOVE_NONE = -1;
    public static final int PLAYER_LEFT = 0;
    public static final int PLAYER_RIGHT = 1;
    public static final int PLAYER_UP = 2;
    public static final int PLAYER_DOWN = 3;
    public static final int PLAYER_JUMP = 4;
    public static final int PLAYER_DODGE = 5;
    public static final int PLAYER_ATTACK_LIGHT = 6;
    public static final int PLAYER_ATTACK_HEAVY = 7;
    public static final int PLAYER_ATTACK_RANGED = 8;
    public static final int PLAYER_ABILITY_1 = 9;
    public static final int PLAYER_ABILITY_2 = 10;
    public static final int PLAYER_ABILITY_SPECIAL = 11;
    public static final int PLAYER_BLOCK = 12;
    public static final int PLAYER_INTERACT = 13;
    public static final int PLAYER_AIM = 14;
    public static final int GAME_MENU = 15;
    public static final int ESC_MENU = 16;
    public static final int FAVS_LEFT = 17;
    public static final int FAVS_UP = 18;
    public static final int FAVS_RIGHT = 19;
    public static final int FAVS_DOWN = 20;
    public static final int PAN_LEFT = 21;
    public static final int PAN_RIGHT = 22;
    public static final int PAN_UP = 23;
    public static final int PAN_DOWN = 24;

    public static final int DAMAGE = 30;
    public static final int HEAL = 31;
    public static final int XP_GAIN = 32;
    public static final int LEVEL_UP = 33;



    /**
     * Meaningful name for an action code
     */
    public static String toString(int actionCode) {
        switch (actionCode) {
            case PLAYER_MOVE_NONE:
                return "Player Stop Moving";
            case PLAYER_LEFT:
                return "Player Left";
            case PLAYER_RIGHT:
                return "Player Right";
            case PLAYER_JUMP:
                return "Player Jump";
            case PLAYER_DODGE:
                return "Player Dodge";
            case PLAYER_ATTACK_LIGHT:
                return "Player Attack Light";
            case PLAYER_ATTACK_HEAVY:
                return "Player Attack Heavy";
            case PLAYER_ATTACK_RANGED:
                return "Player Attack Ranged";
            case PLAYER_ABILITY_1:
                return "Player Ability 1";
            case PLAYER_ABILITY_2:
                return "Player Ability 2";
            case PLAYER_ABILITY_SPECIAL:
                return "Player Special Ability";
            case PLAYER_BLOCK:
                return "Player Block";
            case PLAYER_INTERACT:
                return "Player Interact";
            case PLAYER_AIM:
                return "Player Aim";
            case GAME_MENU:
                return "Game Menu";
            case ESC_MENU:
                return "ESC/Pause Menu";
            case FAVS_LEFT:
                return "Favourites Left";
            case FAVS_UP:
                return "Favourites Up";
            case FAVS_RIGHT:
                return "Favourites Right";
            case FAVS_DOWN:
                return "Favourites Down";
            case PAN_LEFT:
                return "Pan Camera Left";
            case PAN_RIGHT:
                return "Pan Camera Right";
            case PAN_UP:
                return "Pan Camera Left";
            case PAN_DOWN:
                return "Pan Camera Right";
            default:
                return null;
        }
    }
}
