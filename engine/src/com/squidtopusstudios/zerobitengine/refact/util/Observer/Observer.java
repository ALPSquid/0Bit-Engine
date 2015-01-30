package com.squidtopusstudios.zerobitengine.refact.util.Observer;


import java.util.Map;

/**
 * Base observer interface
 */
public interface Observer {

    /**
     *
     * @param obs The Observable instance that has changed
     * @param data The changed data from the Observable
     */
    public void update(Observable obs, Map<String, Object> data);
}
