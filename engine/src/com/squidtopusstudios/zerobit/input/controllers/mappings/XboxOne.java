package com.squidtopusstudios.zerobit.input.controllers.mappings;

import com.badlogic.gdx.controllers.PovDirection;

/**
 * Xbox One Controller Mappings
 */
public class XboxOne {

    public static final String ID = "Microsoft X-Box One pad";
    public static final int BUTTON_A = 0;
    public static final int BUTTON_B = 1;
    public static final int BUTTON_X = 2;
    public static final int BUTTON_Y = 3;
    public static final int BUTTON_LB = 4;
    public static final int BUTTON_RB = 5;
    public static final int BUTTON_BACK = 6;
    public static final int BUTTON_START = 7;
    public static final int BUTTON_XBOX = 8;
    public static final int BUTTON_JOYSTICK_LEFT = 9;
    public static final int BUTTON_JOYSTICK_RIGHT = 10;
    public static final PovDirection BUTTON_DPAD_UP = PovDirection.north;
    public static final PovDirection BUTTON_DPAD_RIGHT = PovDirection.east;
    public static final PovDirection BUTTON_DPAD_DOWN = PovDirection.south;
    public static final PovDirection BUTTON_DPAD_LEFT = PovDirection.west;
    //public static final int AXIS_LEFT_X = 0;        // -1 left, + 1 right
    //public static final int AXIS_LEFT_Y = 1;        // -1 up, +1 down
    public static final int AXIS_LEFT_X = 1;        // -1 left, + 1 right
    public static final int AXIS_LEFT_Y = 0;        // -1 up, +1 down
    public static final int AXIS_LEFT_TRIGGER = 2;  // -1 to 1
    //public static final int AXIS_RIGHT_X = 3;       // -1 left, + 1 right
    //public static final int AXIS_RIGHT_Y = 4;       // -1 up, +1 down
    public static final int AXIS_RIGHT_X = 4;       // -1 left, + 1 right
    public static final int AXIS_RIGHT_Y = 3;       // -1 up, +1 down
    public static final int AXIS_RIGHT_TRIGGER = 5; // -1 to 1
}
