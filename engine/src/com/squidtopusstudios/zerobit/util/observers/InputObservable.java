package com.squidtopusstudios.zerobit.util.observers;

/**
 * An {@link Observable} that dispatches game input events
 */
public interface InputObservable {

    public abstract void registerObserver(InputObserver o);
    public abstract void removeObserver(InputObserver o);

}
