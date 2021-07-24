package com.github.lory24.mcuitils.utils;

import lombok.Getter;
import org.bukkit.enchantments.Enchantment;

public class ItemEnchant {
    @Getter private final Enchantment enchant;
    @Getter private final int level;
    @Getter private final boolean ignoreLimit;

    /**
     * The constructor for the ItemEnchant object
     * @param enchant The enchantment
     * @param level The enchantment's level
     * @param ignoreLimit If you want to ignore the level limit
     */
    public ItemEnchant(Enchantment enchant, int level, boolean ignoreLimit) {
        this.enchant = enchant;
        this.level = level;
        this.ignoreLimit = ignoreLimit;
    }
}
