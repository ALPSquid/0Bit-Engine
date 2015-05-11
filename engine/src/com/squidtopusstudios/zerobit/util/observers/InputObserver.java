package com.squidtopusstudios.zerobit.util.observers;

/**
 * Observer interface for {@link InputObservable}s
 */
public interface InputObserver {

    /**
     * Called by {@link InputObservable} that this object is registered with
     * @param event event code mapped from user input
     */
    public abstract void inputEvent(int event);
}
