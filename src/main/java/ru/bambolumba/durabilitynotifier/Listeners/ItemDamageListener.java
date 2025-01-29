package ru.bambolumba.durabilitynotifier.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Utils.DurabilityUtil;

public class ItemDamageListener implements Listener {

    private final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {

        DurabilityUtil durabilityUtil = plugin.getDurabilityUtil();

        ItemStack itemStack = event.getItem();
        Player player = event.getPlayer();

        if (!player.hasPermission("durability.notify")) {
            return;
        }

        Damageable damageable = (Damageable) itemStack.getItemMeta();
        int durability = durabilityUtil.getDurability(itemStack, damageable) - event.getDamage();

        if (durability <= 0) {
            durabilityUtil.sendBreakNotification(player, itemStack, durability);
        } else {
            durabilityUtil.sendDamageNotification(player, itemStack, durability);
        }

    }

}
