package com.squidtopusstudios.zerobit.tools.keymapper;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class KeyMapperGUI extends JFrame implements ApplicationListener, InputProcessor, ControllerListener {

    public static int SCREEN_WIDTH = 600;
    public static int SCREEN_HEIGHT = 750;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        KeyMapperGUI frame = new KeyMapperGUI();

        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setTitle("0bit Engine - KeyMapper GUI");
        frame.setLocationRelativeTo(null); // Center window
        // Create frame content
        frame.createGUI();
        frame.setVisible(true);
    }
    // ---

    private KeyMapper keyMapper;


    public KeyMapperGUI() {
        keyMapper = new KeyMapper();
        try {
            keyMapper.loadActionMap("example/actions.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createGUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container window = getContentPane();

        JPanel pnlMap2 = new JPanel(); // Controller map panel

        JTabbedPane noteBook = new JTabbedPane();
        noteBook.addTab("Kbd & Mouse", createMapPanel());
        noteBook.addTab("Controller", pnlMap2);

        window.add(noteBook);
    }

    private JPanel createMapPanel() {
        JPanel pnl = new JPanel();
        pnl.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(keyMapper.actions.size(), 2, 35, 5));
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        for (String action : keyMapper.actions.keySet()) {
            contentPanel.add(new JLabel(action));
            contentPanel.add(new JTextField());
        }
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        pnl.add(scrollPane);
        return pnl;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void create() {

    }

    @Override
    public void render() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
