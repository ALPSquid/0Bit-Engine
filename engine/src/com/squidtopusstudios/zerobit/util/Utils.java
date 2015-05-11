package com.squidtopusstudios.zerobit.util;

import java.util.Random;


public class Utils {

    private static Random random = new Random();


    public static int randRange(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static float randRange(float min, float max) {
        return (random.nextFloat() * (max - min)) + min;
    }
}
