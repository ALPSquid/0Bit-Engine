package com.squidtopusstudios.zerobit.util.observers;

public interface Observable {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}