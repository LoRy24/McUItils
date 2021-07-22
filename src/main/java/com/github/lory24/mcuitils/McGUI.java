package com.github.lory24.mcuitils;

import com.github.lory24.mcuitils.api.GUIButton;
import com.github.lory24.mcuitils.api.GUIButtonEvents;
import com.github.lory24.mcuitils.api.GUICustomItem;
import com.github.lory24.mcuitils.utils.GuiLines;
import lombok.Getter;
import com.github.lory24.mcuitils.api.GUItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

@SuppressWarnings("unused")
public class McGUI implements Listener {

    @Getter private final GuiLines invSize;
    private final Inventory inventory;
    private final HashMap<Integer, GUIButton> buttons;

    /**
     * The constructor for the McGUI object.
     * @param name The display-name of the guy
     * @param invSize The size of the inventory
     * @param plugin The main class of the plugin that uses this API
     */
    public McGUI(final String name, GuiLines invSize,
                 Plugin plugin) {
        this.invSize = invSize;
        inventory = Bukkit.createInventory(null, this.invSize.getSize(), name);
        this.buttons = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Method to open this GUI to a player
     * @param player The player that you want to open the gui to
     */
    public void openInventoryTo(final Player player) {
        player.openInventory(inventory);
    }

    /**
     * Method to fill all the empty slots of the guy with a precise object
     * @param m The material of the block
     * @param name The display name of the block
     */
    public void fillBlacksWith(Material m, String name) {
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
        buttons.put(index, new GUIButton(item, events));
        this.inventory.setItem(index, item.buildToItemStack());
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
        if (!this.buttons.containsKey(event.getSlot())) return;
        if (this.buttons.get(event.getRawSlot()).getButtonEvents().isTwoClicksTypes()) {
            if (event.getClick().isLeftClick()) {
                this.buttons.get(event.getSlot()).getButtonEvents().getAClick().run();
            } else if (event.getClick().isRightClick()) this.buttons.get(event.getSlot()).getButtonEvents()
                    .getBClick().run();
            return;
        }
        this.buttons.get(event.getSlot()).getButtonEvents()
                .getAClick().run();
    }
}
