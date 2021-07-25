package com.github.lory24.mcuitils.api.animating;

import com.github.lory24.mcuitils.api.GUItem;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ItemAnimation {
    @Getter private final List<AnimBlock> animationParts;
    @Getter private final int itemIndex;
    @Getter private final boolean looped;

    /**
     * The constructor for the ItemAnimation object
     * @param itemIndex The index of the item that will be animated
     * @param looped If the animation is looped
     */
    public ItemAnimation(int itemIndex, boolean looped) {
        this.itemIndex = itemIndex;
        this.looped = looped;
        this.animationParts = new ArrayList<>();
    }

    /**
     * Method to execute the animation step by step.
     * @param player The player that has the inventory where you want to animate the item open
     * @param inventory The main inventory
     */
    @SuppressWarnings("BusyWait")
    public void executeAnimation(final Plugin plugin, final Player player, Inventory inventory) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            do {
                for (AnimBlock b : animationParts) {
                    switch (b.getType()) {
                        case TIMEOUT: {
                            try {
                                Thread.sleep(b.getA());
                            } catch (InterruptedException e) { e.printStackTrace(); }
                        }

                        case SET_BLOCK: {
                            try {
                                player.getOpenInventory().getTopInventory().setItem(itemIndex, b.getB());
                            } catch (Exception e) { e.printStackTrace(); }
                        }

                        default: break;
                    }
                }
            } while (player.getOpenInventory().getTopInventory()
                    .equals(inventory) && looped);
        });
    }

    /**
     * Method to add a timeout in the animation
     * @param ticks The ticks for the timeout
     * @return the updated object
     */
    public ItemAnimation addTimeout(int ticks) {
        animationParts.add(new AnimBlock(ticks));
        return this;
    }

    /**
     * Method to add a new item-replacement in the animation.
     * @param item The item to place at the original item's index
     * @return the updated object
     */
    public ItemAnimation addReplacement(ItemStack item) {
        animationParts.add(new AnimBlock(item));
        return this;
    }
}
