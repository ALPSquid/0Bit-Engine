package com.squidtopusstudios.zerobitengine.utils;

import com.badlogic.gdx.graphics.Color;

/**
 * Handy utilities
 */
public class Utils {

    public static class Colour {
        /**
         * Note: Before anyone complains,  I've had to use 'color' for years so I'm going
         * to damn well use the correct spelling in my own engine ;) ... I'll probably change it back...
         * @param r red value (0-255)
         * @param g green value (0-255)
         * @param b blue value (0-255)
         * @param a alpha value (0-1)
         */
        public static Color fromRGBA (int r, int g, int b, float a){
            return new Color(r / 255f, g / 255f, b / 255f, a);
        }
    }
}
