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
    private final DurabilityUtil durabilityUtil = plugin.getDurabilityUtil();

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {

        ItemStack itemStack = event.getItem();
        Player player = event.getPlayer();

        if (!player.hasPermission("durability.notify")) {
            return;
        }

        if (durabilityUtil.getDurability(itemStack, (Damageable) itemStack.getItemMeta()) - event.getDamage() <= 0) {
            durabilityUtil.sendBreakNotification(player, itemStack);
        } else {
            durabilityUtil.sendDamageNotification(player, itemStack);
        }

    }

}
