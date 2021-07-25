package com.github.lory24.mcuitils.api.animating;

import lombok.Getter;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class ItemAnimation {
    @Getter private final List<AnimBlock> animationParts;

    public ItemAnimation() {
        this.animationParts = new ArrayList<>();
    }

    public void executeAnimation(final Inventory inventory) {

    }
}
