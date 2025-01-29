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
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Utils.DurabilityUtil;
import ru.bambolumba.durabilitynotifier.Utils.MessageUtil;

public class BlockBreakListener implements Listener {

    private final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);

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
        MessageType messageType = plugin.getMessage();
        ActionBarType actionBarType = plugin.getActionBar();

        if (DurabilityUtil.isDurabilityLow(durability)) {

            String itemName = MessageUtil.removeBrackets(PlainTextComponentSerializer.plainText().serialize(itemStack.displayName()));

            if (messageType.isEnabled()) {
                player.sendMessage(MessageUtil.build(messageType.getText(), "{item}", itemName));
            }

            if (actionBarType.isEnabled()) {
                player.sendMessage(MessageUtil.build(actionBarType.getText(), "{item}", itemName));
            }

        }

    }

}
