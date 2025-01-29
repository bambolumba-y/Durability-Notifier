package ru.bambolumba.durabilitynotifier.Listeners;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Notifications.ActionBarType;
import ru.bambolumba.durabilitynotifier.Notifications.MessageType;
import ru.bambolumba.durabilitynotifier.Utils.DurabilityUtil;
import ru.bambolumba.durabilitynotifier.Utils.MessageUtil;

public class DamageListener implements Listener {

    private final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
    private final MessageType messageType = plugin.getMessage();
    private final ActionBarType actionBarType = plugin.getActionBar();

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player player)) {
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack.getType() == Material.AIR) {
            return;
        }

        DurabilityUtil.sendNotification(player, itemStack);

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

        DurabilityUtil.sendNotification(player, helmet);
        DurabilityUtil.sendNotification(player, chestplate);
        DurabilityUtil.sendNotification(player, leggings);
        DurabilityUtil.sendNotification(player, boots);

    }

}
