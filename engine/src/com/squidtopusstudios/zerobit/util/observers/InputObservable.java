package com.squidtopusstudios.zerobit.util.observers;

/**
 * An {@link Observable} that dispatches game input events
 */
public interface InputObservable {

    void registerObserver(InputObserver o);
    void removeObserver(InputObserver o);
    void notifyObservers(int event);

}