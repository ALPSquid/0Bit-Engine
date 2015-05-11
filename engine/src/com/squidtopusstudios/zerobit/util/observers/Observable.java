package com.squidtopusstudios.zerobit.util.observers;

public interface Observable {
    public abstract void registerObserver(Observer o);
    public abstract void removeObserver(Observer o);
    public abstract void notifyObservers();
}