package com.squidtopusstudios.zerobitengine.utils;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    /** @return an array consisting of an array of x values and an array of y values **/
    public static float[][] splitXY(float... vertices) {
        float[] xCoords = new float[vertices.length/2];
        float[] yCoords = new float[vertices.length/2];
        int xArrayIndex = 0;
        int yArrayIndex = 0;
        for (int i=0; i < vertices.length; i++) {
            if ((i & 1) == 0) {
                xCoords[xArrayIndex] = vertices[i];
                xArrayIndex++;
            } else {
                yCoords[yArrayIndex] = vertices[i];
                yArrayIndex++;
            }
        }
        return new float[][]{xCoords, yCoords};
    }

    /** @return this min and max value of a float array **/
    public static float[] minMax(float... values) {
        float min = 0;
        float max = 0;
        for (float value : values) {
            if (value < min) min = value;
            else if (value > max) max = value;
        }
        return new float[]{min, max};
    }

    /** Moves the list of vertices to be an equal amount either side of 0 **/
    public static float[] normalize(float... vertices) {
        float[][] coords = splitXY(vertices);
        float[] minMaxX = minMax(coords[0]);
        float[] minMaxY = minMax(coords[1]);

        float offsetX = (Math.abs(minMaxX[1]) - Math.abs(minMaxX[0]))/2;
        float offsetY = (Math.abs(minMaxY[1]) - Math.abs(minMaxY[0]))/2;
        for (int i=0; i < vertices.length; i++) {
            if ((i & 1) == 0) vertices[i] -= offsetX;
            else vertices[i] -= offsetY;
        }
        return vertices;
    }
}
