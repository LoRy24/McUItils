package com.github.lory24.mcuitils.api;

import com.github.lory24.mcuitils.utils.ItemEnchant;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

@SuppressWarnings("unused")
public class GUIHead extends GUItem {

    @Getter private String skullOwner;
    @Getter private boolean customTexture = false;
    @Getter private String texture;

    /**
     * A simple constructor for a GUItem but with only the material
     * (Default name, amount and lore)
     */
    public GUIHead() {
        super(Material.SKULL_ITEM);
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
     * Set a custom texture to the head
     * @param v The Base64 encoded texture for the skull
     * @return The updated GuiItem (this)
     */
    public GUIHead setCustomTexture(String v) {
        this.customTexture = true;
        this.texture = v;
        return this;
    }

    @Override
    public ItemStack parseCustomizations() {
        ItemStack i = this.buildToItemStack();
        SkullMeta meta = (SkullMeta) this.getItemMeta();

        // Set custom texture
        if (isCustomTexture()) {
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", this.texture));
            try {
                Field field = meta.getClass().getDeclaredField("profile");
                field.setAccessible(true);
                field.set(meta, profile);
            } catch (Exception e) { e.printStackTrace(); }
        }

        i.setItemMeta(meta);
        return i;
    }
}
