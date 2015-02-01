package com.squidtopusstudios.zerobitengine.refact.util.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A suggested base class for Observables. However, if you need an interface instead, just implement Observable
 */
public abstract class ZbeObservable implements Observable {
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    private final Map<String, Object> data = new HashMap<String, Object>();


    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    /**
     * Sends this instance and a data HashMap<String, Object> to all Observers, use for a push implementation.<br/>
     * The data map is cleared once all observers have been notified.
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
    public void addObserverData(String key, Object data) {
        this.data.put(key, data);
    }
}
