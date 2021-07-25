package com.github.lory24.mcuitils;

import com.github.lory24.mcuitils.api.*;
import com.github.lory24.mcuitils.utils.GuiLines;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public class McGUI implements Listener {

    @Getter private final GuiLines invSize;
    private final Inventory inventory;
    private final HashMap<Integer, GUIButton> buttons;
    private final List<GUIAnimatedItem> animItems;
    private final Plugin plugin;

    /**
     * The constructor for the McGUI object.
     * @param name The display-name of the guy
     * @param invSize The size of the inventory
     * @param plugin The main class of the plugin that uses this API
     */
    public McGUI(final String name, GuiLines invSize,
                 Plugin plugin) {
        this.invSize = invSize;
        inventory = invSize != GuiLines.ONE_LINE_FIVE_SLOTS ? Bukkit.createInventory(null, this.invSize.getSize(), name) :
                Bukkit.createInventory(null, InventoryType.HOPPER, name);
        this.buttons = new HashMap<>();
        this.animItems = new ArrayList<>();
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    /**
     * Method to open this GUI to a player
     * @param player The player that you want to open the gui to
     */
    public void openInventoryTo(@NotNull final Player player) {
        player.openInventory(inventory);
        for (GUIAnimatedItem i : animItems) i.getAnimation().executeAnimation(this.plugin, player, this.inventory);
    }

    /**
     * Method to fill all the empty slots of the guy with a precise object
     * @param m The material of the block
     * @param name The display name of the block
     */
    public void fillBlanksWith(Material m, String name) {
        for (int i = 0; i < invSize.getSize(); i++) {
            if (inventory.getItem(i) == null) inventory.setItem(i, new GUICustomItem(m).setName(name).buildToItemStack());
        }
    }

    /**
     * Method used to create a Button to insert into the guy
     * @param item The Special Item to add
     * @param index The slot where you want to put the item
     * @param events The events listener for when you interact with this button
     */
    public void createButton(final GUItem item, int index, final GUIButtonEvents events) {
        buttons.remove(index);
        buttons.put(index, new GUIButton(item, events, index));
        inventory.setItem(index, item.buildToItemStack());
    }

    /**
     * Method used to create an Animated Button and insert it into the GUI
     * @param item The animated item object
     * @param index The index of the item: It's where you want to put the item into the GUI
     * @param events The events listener for when you interact with this button
     */
    public void createAnimatedButton(final GUIAnimatedItem item, int index, final GUIButtonEvents events) {
        buttons.remove(index);
        buttons.put(index, new GUIButton(item.getDefaultItem(), events, index));
        inventory.setItem(index, item.getDefaultItem().buildToItemStack());
        animItems.remove(item);
        animItems.add(item);
    }

    /**
     * The GUI event listening method
     * @param event The event
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(this.inventory)) return;
        event.setCancelled(true);

        if (event.getCurrentItem() == null) return;
        if (!this.buttons.containsKey(event.getRawSlot())) return;

        if (this.buttons.get(event.getRawSlot()).getButtonEvents().isTwoClicksTypes()) {
            if (event.getClick().isLeftClick()) {
                this.buttons.get(event.getRawSlot()).getButtonEvents().getAClick().run();
            } else if (event.getClick().isRightClick()) this.buttons.get(event.getRawSlot()).getButtonEvents()
                    .getBClick().run();
            return;
        }
        this.buttons.get(event.getRawSlot()).getButtonEvents()
                .getAClick().run();
    }
}
