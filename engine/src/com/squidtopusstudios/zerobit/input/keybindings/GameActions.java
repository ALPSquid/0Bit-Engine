package com.squidtopusstudios.zerobit.input.keybindings;

/**
 * Example Game actions for key mapping and event checking. Events dispatched by {@link com.squidtopusstudios.zerobit.input.InputMapper} correspond to the actions found in this class.
 */
public class GameActions {
    // TODO: Docs and a way to add new actions

    // 0 should not be used due to the way released keys are sent as the negative value
    public static final int PLAYER_LEFT = 1;
    public static final int PLAYER_RIGHT = 2;
    public static final int PLAYER_UP = 3;
    public static final int PLAYER_DOWN = 4;
    public static final int PLAYER_JUMP = 5;
    public static final int PLAYER_DODGE = 6;
    public static final int PLAYER_ATTACK_LIGHT = 7;
    public static final int PLAYER_ATTACK_HEAVY = 8;
    public static final int PLAYER_ATTACK_RANGED = 9;
    public static final int PLAYER_ABILITY_1 = 10;
    public static final int PLAYER_ABILITY_2 = 11;
    public static final int PLAYER_ABILITY_SPECIAL = 12;
    public static final int PLAYER_BLOCK = 13;
    public static final int PLAYER_INTERACT = 14;
    public static final int PLAYER_AIM = 15;
    public static final int GAME_MENU = 16;
    public static final int ESC_MENU = 17;
    public static final int FAVES_LEFT = 18;
    public static final int FAVES_UP = 19;
    public static final int FAVES_RIGHT = 20;
    public static final int FAVES_DOWN = 21;
    public static final int PAN_LEFT = 22;
    public static final int PAN_RIGHT = 23;
    public static final int PAN_UP = 24;
    public static final int PAN_DOWN = 25;

    public static final int DAMAGE = 30;
    public static final int HEAL = 31;
    public static final int XP_GAIN = 32;
    public static final int LEVEL_UP = 33;



    /**
     * Meaningful name for an action code
     */
    public static String toString(int actionCode) {
        if (actionCode < 0) {
            return toString(Math.abs(actionCode)) + " Released";
        }
        switch (actionCode) {
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
            case FAVES_LEFT:
                return "Favourites Left";
            case FAVES_UP:
                return "Favourites Up";
            case FAVES_RIGHT:
                return "Favourites Right";
            case FAVES_DOWN:
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
                return "Unmapped";
        }
    }
}
