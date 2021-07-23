package com.github.lory24.mcuitils.api;

import lombok.Getter;

public class GUIButton {
    @Getter private final GUItem item;
    @Getter private final GUIButtonEvents buttonEvents;
    @Getter private final int index;

    /**
     * The constructor to make a GUI button
     * @param item The GUItem that you want to use as gui item of the button
     * @param buttonEvents The events for when you click the button
     * @param index The position into the inventory
     */
    public GUIButton(GUItem item, GUIButtonEvents buttonEvents, int index) {
        this.item = item;
        this.buttonEvents = buttonEvents;
        this.index = index;
    }
}
