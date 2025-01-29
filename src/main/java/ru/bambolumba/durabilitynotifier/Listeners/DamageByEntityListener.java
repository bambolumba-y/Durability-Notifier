package ru.bambolumba.durabilitynotifier.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Utils.DurabilityUtil;

public class DamageByEntityListener implements Listener {

    private final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
    private final DurabilityUtil durabilityUtil = plugin.getDurabilityUtil();

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player player)) {
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack.getType() == Material.AIR) {
            return;
        }

        durabilityUtil.sendNotification(player, itemStack);

    }

    @EventHandler
    public void onDefense(EntityDamageByEntityEvent event) {

        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        ItemStack helmet = player.getEquipment().getItem(EquipmentSlot.HEAD);
        ItemStack chestplate = player.getEquipment().getItem(EquipmentSlot.CHEST);
        ItemStack leggings = player.getEquipment().getItem(EquipmentSlot.LEGS);
        ItemStack boots = player.getEquipment().getItem(EquipmentSlot.FEET);

        durabilityUtil.sendNotification(player, helmet);
        durabilityUtil.sendNotification(player, chestplate);
        durabilityUtil.sendNotification(player, leggings);
        durabilityUtil.sendNotification(player, boots);

    }

}
