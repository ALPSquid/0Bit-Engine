package com.squidtopusstudios.zerobit.tools.keymapper.ui;


import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.tabbedpane.Tab;

public class MapTab extends Tab {

    private String title;
    private VisTable content;


    public MapTab(String title) {
        super(true, false);
        this.title = title;
        content = new VisTable();
        content.add(new VisLabel("Test"));
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public Table getContentTable() {
        return content;
    }
}
