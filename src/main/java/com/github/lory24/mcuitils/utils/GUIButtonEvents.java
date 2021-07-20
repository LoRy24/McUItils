package com.github.lory24.mcuitils.utils;

import lombok.Getter;

@SuppressWarnings("unused")
public class GUIButtonEvents {
    @Getter private final boolean twoClicksTypes;
    @Getter private final Runnable aClick;
    @Getter private Runnable bClick;

    /**
     * Constructor for when you want to use the same event for all the inventory interacts
     * @param aClick The event runnable
     */
    public GUIButtonEvents(Runnable aClick) {
        this.aClick = aClick;
        this.twoClicksTypes = false;
    }

    /**
     * Constructor for when you want to make an event for the left click and another event
     * for the right click
     * @param aClick The left click event's actions
     * @param bClick The right click event's actions
     */
    public GUIButtonEvents(Runnable aClick, Runnable bClick) {
        this.aClick = aClick;
        this.bClick = bClick;
        this.twoClicksTypes = true;
    }
}
