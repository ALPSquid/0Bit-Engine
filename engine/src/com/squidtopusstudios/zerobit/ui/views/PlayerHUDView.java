package com.squidtopusstudios.zerobit.ui.views;

import com.squidtopusstudios.zerobit.ui.controllers.PlayerHUDController;
import com.squidtopusstudios.zerobit.util.observers.Observable;

import java.util.Map;

/**
 * Play HUD View
 * TODO: Move to view examples
 */
public class PlayerHUDView extends UIView {

    private PlayerHUDController controller;

    public PlayerHUDView(PlayerHUDController controller) {
        super(controller);
        this.controller = controller;
    }

    public void loadUI() {}

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void update(Observable obs, Map<String, Object> data) {
    }
}
