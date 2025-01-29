package ru.bambolumba.durabilitynotifier.Utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

public class DurabilityUtil {

    public static int getDurability(ItemStack itemStack, Damageable damageable) {
        return itemStack.getType().getMaxDurability() - damageable.getDamage();
    }

    public static boolean isDurabilityLow(int durability) {
        return durability < ConfigManager.getConfig().getInt("notifications.required-durability");
    }

}
