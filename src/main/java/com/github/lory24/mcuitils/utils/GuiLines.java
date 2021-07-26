package com.github.lory24.mcuitils.utils;

import lombok.Getter;

@SuppressWarnings("unused")
public enum GuiLines {
    ONE_LINE_FIVE_SLOTS(5),
    ONE_LINE(9),
    TWO_LINES(18),
    THREE_LINES(27),
    THREE_LINES_NINE_SLOTS(9),
    FOUR_LINES(36),
    FIVE_LINES(45),
    SIX_LINES(54);

    /**
     * The selected size value
     */
    @Getter private final int size;

    /**
     * The constructor for this enum.
     */
    GuiLines(int size) {
        this.size = size;
    }
}
