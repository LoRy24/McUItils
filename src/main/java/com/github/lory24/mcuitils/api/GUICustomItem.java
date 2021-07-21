package com.github.lory24.mcuitils.api;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUICustomItem extends GUItem {

    /**
     * A simple constructor for a GUItem but with only the material (Default name, amount
     * and lore)
     * @param material The material of the item
     */
    public GUICustomItem(Material material) {
        super(material);
    }

    /**
     * Method to put all the precedent item options into a new ItemStack and then return it
     * @return The final item
     */
    @Override
    public ItemStack buildToItemStack() {
        ItemStack i = new ItemStack(getMaterial(), getAmount(), (byte) getType());
        ItemMeta meta = i.getItemMeta();
        if (getName() != null) meta.setDisplayName(getName());
        meta.setLore(getLore());
        i.setItemMeta(meta);
        return i;
    }
}