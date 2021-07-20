package com.github.lory24.mcuitils.utils;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

@SuppressWarnings("unused")
public class GUIHead extends GUItem {

    @Getter private String skullOwner;

    /**
     * A simple constructor for a GUItem but with only the material
     * (Default name, amount and lore)
     */
    public GUIHead() {
        super(Material.SKULL);
    }

    /**
     * Set the owner of the head. Used also if you want to set the head texture as the skin of a
     * player
     * @param owner The name of the owner's head
     * @return The updated GUItem (this)
     */
    public GUIHead setSkullOwner(String owner) {
        this.skullOwner = owner;
        return this;
    }

    /**
     * Method to put all the precedent item options into a new ItemStack and then
     * return it
     * @return The final item
     */
    @Override
    public ItemStack buildToItemStack() {
        ItemStack i = new ItemStack(getMaterial(), getAmount(), (byte) getType());
        SkullMeta meta = (SkullMeta) i.getItemMeta();
        if (getName() != null) meta.setDisplayName(getName());
        if (getSkullOwner() != null) meta.setOwner(getSkullOwner());
        meta.setLore(getLore());
        i.setItemMeta(meta);
        return i;
    }
}
