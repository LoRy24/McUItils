package com.github.lory24.mcuitils.api.animating;

import com.github.lory24.mcuitils.api.GUItem;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

public class AnimBlock {
    @Getter private final AnimCompType type;
    @Getter private int a; // Used for Timeouts
    @Getter private ItemStack b; // For Items

    /**
     * A constructor for the AnimBlock for when it is for a timeout
     * @param a The timeout time
     */
    public AnimBlock(int a) {
        this.type = AnimCompType.TIMEOUT;
        this.a = a;
    }

    /**
     * A constructor for the AnimBlock for when it is for an item replacement
     * @param b The GUItem that you want to use as replacement
     */
    public AnimBlock(ItemStack b) {
        this.type = AnimCompType.SET_BLOCK;
        this.b = b;
    }
}
