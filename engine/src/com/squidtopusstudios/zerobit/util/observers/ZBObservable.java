package com.squidtopusstudios.zerobit.util.observers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Observable implementation for classes that don't need to extend another class<br/>
 * Implements the {@link Observable} interface to allow {@link Observer}s, such as a {@link com.squidtopusstudios.zerobit.ui.views.View}, to receive updates.<br/>
 * See {@link Observable} for observable usage.
 */
public class ZBObservable implements Observable {

    private List<Observer> observers = new ArrayList<>();
    private final Map<String, Object> data = new HashMap<String, Object>();


    public void registerObserver(Observer o) {
        if (!observers.contains(o)) observers.add(o);
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
