package com.github.lory24.mcuitils.api;

import com.github.lory24.mcuitils.api.animating.ItemAnimation;
import lombok.Getter;

public class GUIAnimatedItem {
    @Getter private final GUItem defaultItem;
    @Getter private final ItemAnimation animation;

    /**
     * The constructor for the GUIAnimatedItem object
     * @param defaultItem The default Item that will be placed into the inventory at its creation
     * @param animation The animator for the item
     */
    public GUIAnimatedItem(GUItem defaultItem, ItemAnimation animation) {
        this.defaultItem = defaultItem;
        this.animation = animation;
    }
}
