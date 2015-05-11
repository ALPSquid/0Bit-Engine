package com.squidtopusstudios.zerobit.ui.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.squidtopusstudios.zerobit.ZeroBit;
import com.squidtopusstudios.zerobit.ui.controllers.DebugController;
import com.squidtopusstudios.zerobit.util.AssetUtils;
import com.squidtopusstudios.zerobit.util.observers.Observable;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Debug Overlay to display key information
 * TODO: Add way for developer to add their own debug information
 */
public class DebugUIView extends UIView {

    private ThreadMXBean threadBean;
    private long lastTime;
    private long lastThreadTime;
    private double smoothLoad;
    private float time = 0;
    private int cpuPercent = -1;

    private DebugController controller;
    private Label txtCodename;
    private Label txtFPS;
    private Label txtCPU;
    private Label txtHeap;
    private Label txtNHeap;
    private Label txtEntities;
    private List<Label> labels;


    public DebugUIView(DebugController controller) {
        super(controller);
        this.controller = controller;
    }

    public void loadUI() {
        threadBean = ManagementFactory.getThreadMXBean();
        if(!threadBean.isThreadCpuTimeEnabled()) threadBean.setThreadCpuTimeEnabled(true);
        lastTime = System.nanoTime();
        lastThreadTime = threadBean.getCurrentThreadCpuTime();

        txtCodename = new Label("", AssetUtils.skinDefault);
        txtFPS = new Label("", AssetUtils.skinDefault);
        txtCPU = new Label("", AssetUtils.skinDefault);
        txtHeap = new Label("", AssetUtils.skinDefault);
        txtNHeap = new Label("", AssetUtils.skinDefault);
        txtEntities = new Label("", AssetUtils.skinDefault);

        labels = new ArrayList<Label>();
        labels.add(txtCodename);
        labels.add(txtFPS);
        labels.add(txtCPU);
        labels.add(txtHeap);
        labels.add(txtNHeap);
        labels.add(txtEntities);

        for (int i=0; i < labels.size(); i++) {
            Label label = labels.get(i);
            label.setPosition(0, stage.getHeight() - (label.getPrefHeight() * i));
            stage.addActor(label);
        }

        stage.addListener(new InputListener() {
            public boolean keyDown (InputEvent event, int keycode) {
                if (keycode == ZeroBit.debugKey) {
                    controller.toggleDebug();
                }
                return false;
            }
        });

        ZeroBit.logger.logDebug("Debug overlay enabled, toggle with ' (apostrophe)");
    }

    @Override
    public void render(float delta) {
        if (controller.debugEnabled()) {
            super.render(delta);
            txtFPS.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
            if ((time += delta) >= 1) {
                // Update usage once a second
                txtCPU.setText("CPU: " + getCPUUsage() + "%");
                time = 0;
            }
            txtHeap.setText("Java Heap: " + String.valueOf(Math.round(Gdx.app.getJavaHeap() * 0.000001)) + "MB");
            txtNHeap.setText("Native Heap: " + String.valueOf(Math.round(Gdx.app.getNativeHeap() * 0.000001)) + "MB");
            txtEntities.setText("World Entities: " + controller.getEntities());
        }
    }

    @Override
    public void update(Observable obs, Map<String, Object> data) {

    }

    /**
     * Credit: https://stackoverflow.com/questions/2062440/java-cpu-usage-monitoring/14712892#14712892
     * Call every frame.
     * Unfortunately, this is fairly inaccurate. For example, while jConsole returned an accurate CPU usage of ~4%, this was reporting ~10
     * @return The cpu usage of the last frame
     */
    public int getCPUUsage() {
        if(threadBean.isThreadCpuTimeSupported() ) {
            long time = System.nanoTime();
            long threadTime = threadBean.getCurrentThreadCpuTime();
            double load = (threadTime - lastThreadTime) / (double)(time - lastTime);
            smoothLoad += (load - smoothLoad) * 0.3;
            cpuPercent = (int)(smoothLoad * 100); //TODO: Is it actually half the value?
            lastTime = time;
            lastThreadTime = threadTime;
        }
        else {
            cpuPercent = -1;
        }

        return cpuPercent;
    }
}
