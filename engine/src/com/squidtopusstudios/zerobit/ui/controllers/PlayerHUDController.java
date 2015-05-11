package com.squidtopusstudios.zerobit.ui.controllers;


import com.squidtopusstudios.zerobit.entity.Identifiers;
import com.squidtopusstudios.zerobit.entity.components.MessagingComponent;
import com.squidtopusstudios.zerobit.screens.GameScreen;
import com.squidtopusstudios.zerobit.ui.views.UIView;

/**
 * Example/super class controller for the PlayerHUD
 * TODO: Move to controller examples
 */
public class PlayerHUDController extends UIController {


    public PlayerHUDController(GameScreen screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void setView(UIView view) {
        super.setView(view);
        ((GameScreen)screen).getWorld().getEntities().getEntity(Identifiers.PLAYER).getComponent(MessagingComponent.class).registerObserver(this.view);
    }

    @Override
    public void resetView() {
        ((GameScreen)screen).getWorld().getEntities().getEntity(Identifiers.PLAYER).getComponent(MessagingComponent.class).removeObserver(this.view);
    }
}
