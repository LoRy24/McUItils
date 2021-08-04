package com.github.lory24.mcuitils.api;

import org.bukkit.Material;

public class GUICustomItem extends GUItem {

    /**
     * A simple constructor for a GUItem but with only the material (Default name, amount
     * and lore)
     * @param material The material of the item
     */
    public GUICustomItem(Material material) {
        super(material);
    }
}
