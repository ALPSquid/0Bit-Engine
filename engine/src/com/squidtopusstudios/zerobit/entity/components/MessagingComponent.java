package com.squidtopusstudios.zerobit.entity.components;

import com.squidtopusstudios.zerobit.util.observers.Observable;
import com.squidtopusstudios.zerobit.util.observers.Observer;

import java.util.*;

/**
 * Observable based component for event messaging
 */
public class MessagingComponent extends ZBComponent implements Observable{

    public List<Observer> observers = new ArrayList<>();
    public final Map<String, Object> data = new HashMap<String, Object>();


    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        throw new UnsupportedOperationException("MessagingSystem handles the notifying of ECS observers");
    }

    /**
     * Add data item to be sent to observers when notifyObservers() is called
     */
    public void addObserverData(String key, Object data) {
        this.data.put(key, data);
    }

    @Override
    public void reset() {
        // Possibly remove observers
    }
}
