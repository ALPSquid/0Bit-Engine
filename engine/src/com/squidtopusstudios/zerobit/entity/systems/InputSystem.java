package com.squidtopusstudios.zerobit.entity.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.entity.Box2DUserData;
import com.squidtopusstudios.zerobit.entity.EntityStates;
import com.squidtopusstudios.zerobit.entity.components.*;
import com.squidtopusstudios.zerobit.input.keybindings.GameActions;
import com.squidtopusstudios.zerobit.util.observers.InputObserver;
import com.squidtopusstudios.zerobit.util.observers.Observable;
import com.squidtopusstudios.zerobit.util.observers.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Executes commands best on input events from an {@link com.squidtopusstudios.zerobit.util.observers.InputObservable} <br/>
 * Dispatches messages on certain input events after checks have been performed
 */
public class InputSystem extends EntitySystem implements InputObserver, Observable {

    protected ComponentMapper<MovementComponent> mvm = ComponentMapper.getFor(MovementComponent.class);
    protected ComponentMapper<StateComponent> stm = ComponentMapper.getFor(StateComponent.class);
    //private ComponentMapper<MessagingComponent> msgm = ComponentMapper.getFor(MessagingComponent.class);
    protected MovementComponent mvc;
    protected StateComponent stc;
    //private MessagingComponent msgc;


    public InputSystem() {
        super();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        removeObservers();
        notifyObservers();
    }

    @Override
    public void inputEvent(int event) {
        ZeroBit.logger.logDebug("Received event: " + GameActions.toString(event));
        if (mvc != null) {
            // Events that can be process when paused
            switch (event) {
                case GameActions.PLAYER_MOVE_NONE:
                    mvc.stop = true;
                    mvc.left = false;
                    mvc.right = false;
                    break;
            }
            // Events that should only be processed when not paused
            if (checkProcessing()) {
                switch (event) {
                    case GameActions.PLAYER_LEFT:
                        mvc.left = true;
                        mvc.right = false;
                        break;
                    case GameActions.PLAYER_RIGHT:
                        mvc.right = true;
                        mvc.left = false;
                        break;
                    case GameActions.PLAYER_ATTACK_LIGHT:
                        stc.state = EntityStates.ATTACKING_MELEE;
                        break;
                    case GameActions.PLAYER_JUMP:
                        mvc.jump = true;
                        break;

                    case GameActions.PLAYER_INTERACT:
                        break;
                }
            }
        }
    }

    /**
     * Sets the Entity that will be controlled by this InputSystem
     */
    public void setControllable(Entity entity) {
        mvc = mvm.get(entity);
        stc = stm.get(entity);
        //msgc = msgm.get(entity);
    }

    // Observer stuff
    private List<Observer> observers = new ArrayList<>();
    private List<Observer> observerRemoveQueue = new ArrayList<>();
    private final Map<String, Object> data = new HashMap<String, Object>();
    public void registerObserver(Observer o) {
        if (!observers.contains(o)) observers.add(o);
    }
    public void removeObserver(Observer o) {
        //observers.remove(o);
        observerRemoveQueue.add(o);
    }

    private void removeObservers() {
        for (Observer observer : observerRemoveQueue) {
            observers.remove(observer);
        }
    }

    /**
     * Sends this instance and a data HashMap<String, Object> to all Observers, use for a push implementation.<br/>
     * The data map is cleared once all observers have been notified.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            if (!observerRemoveQueue.contains(observer)) observer.update(this, data);
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
