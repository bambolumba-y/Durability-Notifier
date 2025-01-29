package ru.bambolumba.durabilitynotifier.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Utils.DurabilityUtil;

public class ItemBreakListener implements Listener {

    private final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
    private final DurabilityUtil durabilityUtil = plugin.getDurabilityUtil();

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("durability.notify")) {
            return;
        }

        ItemStack itemStack = event.getBrokenItem();
        durabilityUtil.sendBreakNotification(player, itemStack);

    }

}
