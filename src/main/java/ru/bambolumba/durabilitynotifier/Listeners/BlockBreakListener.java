package ru.bambolumba.durabilitynotifier.Listeners;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import ru.bambolumba.durabilitynotifier.Notifications.ActionBarType;
import ru.bambolumba.durabilitynotifier.Notifications.MessageType;
import ru.bambolumba.durabilitynotifier.ProjectDurability;
import ru.bambolumba.durabilitynotifier.Utils.DurabilityUtil;
import ru.bambolumba.durabilitynotifier.Utils.MessageUtil;

public class BlockBreakListener implements Listener {

    private ProjectDurability plugin = ProjectDurability.getPlugin(ProjectDurability.class);

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack.getType() == Material.AIR) {
            plugin.getLogger().info("No item in hand");
            return;
        }

        if (!(itemStack.getItemMeta() instanceof Damageable damageable)) {
            plugin.getLogger().info("This item is not damageable");
            return;
        }

        int durability = DurabilityUtil.getDurability(itemStack, damageable);
        plugin.getLogger().info("Durability of " + itemStack.displayName() + " = " + durability);
        MessageType messageType = plugin.getMessage();
        ActionBarType actionBarType = plugin.getActionBar();

        if (DurabilityUtil.isDurabilityLow(durability)) {

            String itemName = PlainTextComponentSerializer.plainText().serialize(itemStack.displayName());

            if (messageType.isEnabled()) {
                String finalMessage = messageType.getText().replace("{item}", itemName);
                player.sendMessage(MessageUtil.build(finalMessage, true));
            }

            if (actionBarType.isEnabled()) {
                String finalMessage = actionBarType.getText().replace("{item}", itemName);
                player.sendActionBar(MessageUtil.build(finalMessage, true));
            }

        }

    }

}
