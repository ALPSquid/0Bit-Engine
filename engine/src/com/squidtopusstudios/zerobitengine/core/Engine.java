package com.squidtopusstudios.zerobitengine.core;


/**
 *  Singleton - Manages the system functionality of the engine including subsystem managers
 */
public class Engine {

    private static Engine engineInstance;

    public static Engine getInstance() {
            if (engineInstance == null) {
                engineInstance = new Engine();
            }
            return engineInstance;
        }

        private Engine() {
        }
}
