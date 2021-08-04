package com.github.lory24.mcuitils.api;

import com.github.lory24.mcuitils.utils.ItemEnchant;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public abstract class GUItem {
    @Getter private final Material material;
    @Getter private ItemMeta itemMeta;
    @Getter private String name;
    @Getter private int amount = 1;
    @Getter private List<String> lore = new ArrayList<>();
    @Getter private int type;
    @Getter private final List<ItemEnchant> enchants;
    @Getter private final List<ItemFlag> flags;

    /* ********************************************************************************************************* */

    /**
     * A simple constructor for a GUItem but with only the material (Default name, amount and lore)
     * @param material The material of the item
     */
    public GUItem(Material material) {
        this.material = material;
        this.enchants = new ArrayList<>();
        this.flags = new ArrayList<>();
        this.itemMeta = new ItemStack(material).getItemMeta();
    }

    /**
     * Set the name at the GUItem
     * @param name The name that you want to set
     * @return The updated GUItem
     */
    public GUItem setName(String name) {
        this.name = name;
        this.itemMeta.setDisplayName(name);
        return this;
    }

    /**
     * Set the amount of the GUItem
     * @param a The amount
     * @return The updated GUItem
     */
    public GUItem setAmount(int a) {
        this.amount = a;
        return this;
    }

    /**
     * Set the lore of the GUItem with an "array"
     * @param simpleLore The lore array
     * @return The updated GUItem
     */
    public GUItem setLore(String... simpleLore) {
        this.lore = Arrays.asList(simpleLore);
        this.itemMeta.setLore(Arrays.asList(simpleLore));
        return this;
    }

    /**
     * Set the lore of the GUItem with a List of Strings
     * @param lore The lore Strings List
     * @return The updated GUItem
     */
    public GUItem setLore(List<String> lore) {
        this.lore = lore;
        this.itemMeta.setLore(lore);
        return this;
    }

    /**
     * Set the type of the item (For example when you want red wool)
     * @param t Set the type of the block
     * @return The updated GUItem
     */
    public GUItem setType(int t) {
        this.type = t;
        return this;
    }

    /**
     * Add an enchant to the item
     * @param enchantment The enchantment to add
     * @param level the level of the enchantment
     * @param ignoreLimit if you want to ignore the enchantment limit
     * @return The updated GUItem
     */
    public GUItem addEnchant(Enchantment enchantment, int level, boolean ignoreLimit) {
        enchants.add(new ItemEnchant(enchantment, level, ignoreLimit));
        this.itemMeta.addEnchant(enchantment, level, ignoreLimit);
        return this;
    }

    /**
     * Set the enchants passed in params as the item enchants. The param in this case
     * is an ItemEnchant array
     * @param enchants The enchants that the item has to have
     * @return The updated GUItem
     */
    public GUItem setEnchants(ItemEnchant... enchants) {
        this.enchants.clear();
        this.enchants.addAll(Arrays.asList(enchants));
        for (ItemEnchant itemEnchant : enchants) this.itemMeta.addEnchant(itemEnchant.getEnchant(),
                itemEnchant.getLevel(), itemEnchant.isIgnoreLimit());
        return this;
    }

    /**
     * Set the enchants passed in params as the item enchants. The param in this case is a List of
     * ItemEnchants
     * @param enchants The enchants that the item has to have
     * @return The updated GUItem
     */
    public GUItem setEnchants(List<ItemEnchant> enchants) {
        this.enchants.clear();
        this.enchants.addAll(enchants);
        for (ItemEnchant itemEnchant : enchants) this.itemMeta.addEnchant(itemEnchant.getEnchant(),
                itemEnchant.getLevel(), itemEnchant.isIgnoreLimit());
        return this;
    }

    /**
     * Method to add an ItemFlag to the item.
     * @param flag The flag to add to the item
     * @return The updated GUItem
     */
    public GUItem setFlag(ItemFlag flag) {
        this.flags.add(flag);
        this.itemMeta.addItemFlags(flag);
        return this;
    }

    /**
     * Method to se a custom item meta based on the second meta of the item
     * @param meta The ItemMeta to set
     * @return The updated GUItem
     */
    public GUItem setCustomModelData(ItemMeta meta) {
        this.itemMeta = meta;
        return this;
    }

    /* ********************************************************************************************************* */

    /**
     * Method to put all the precedent item options into a new ItemStack and then return it
     * @return The final item
     */
    public ItemStack buildToItemStack() {
        ItemStack i = new ItemStack(getMaterial(), getAmount(), (byte) getType());
        i.setItemMeta(this.itemMeta);
        return i;
    }

    /**
     * Method to add some customizations to the item.
     * @return TEMPORALLY: the built item stack
     */
    public ItemStack parseCustomizations() {
        return buildToItemStack();
    }
}
