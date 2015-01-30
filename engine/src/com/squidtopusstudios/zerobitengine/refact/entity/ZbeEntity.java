package com.squidtopusstudios.zerobitengine.refact.entity;

import com.badlogic.ashley.core.Entity;
import com.squidtopusstudios.zerobitengine.refact.util.Observer.Observable;
import com.squidtopusstudios.zerobitengine.refact.util.Observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Base Entity class for all engine entities. Extends The Ashley Entity class to provide component and system support.
 * Also implements the {@link Observable} interface to allow {@link Observer}s, such as a {@link com.squidtopusstudios.zerobitengine.refact.ui.View}, to receive updates.
 * See {@link com.squidtopusstudios.zerobitengine.refact.util.Observer.ZbeObservable} for observable usage.
 */
public class ZbeEntity extends Entity implements Observable {
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private final Map<String, Object> data = new HashMap<String, Object>();


    public ZbeEntity() {

    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Sends this instance and a data HashMap<String, Object> to all Observers, use for a push implementation.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this, data);
        }
        data.clear();
    }

    /**
     * Add data item to be sent to observers when notifyObservers() is called
     */
    protected void addObserverData(String key, Object data) {
        this.data.put(key, data);
    }
}
