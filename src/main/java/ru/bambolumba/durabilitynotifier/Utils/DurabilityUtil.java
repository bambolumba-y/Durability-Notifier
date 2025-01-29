package ru.bambolumba.durabilitynotifier.Utils;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Notifications.ActionBarType;
import ru.bambolumba.durabilitynotifier.Notifications.MessageType;

public class DurabilityUtil {

    private static final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
    private static final MessageType messageType = plugin.getMessage();
    private static final ActionBarType actionBarType = plugin.getActionBar();

    public static int getDurability(ItemStack itemStack, Damageable damageable) {
        return itemStack.getType().getMaxDurability() - damageable.getDamage();
    }

    public static boolean isDurabilityLow(int durability) {
        return durability < ConfigManager.getConfig().getInt("notifications.required-durability");
    }

    public static void sendNotification(Player player, ItemStack itemStack) {

        Damageable damageable = (Damageable) itemStack.getItemMeta();

        if (!damageable.hasDamageValue()) {
            return;
        }

        int durability = DurabilityUtil.getDurability(itemStack, damageable);

        if (DurabilityUtil.isDurabilityLow(durability)) {

            String itemName = MessageUtil.removeBrackets(PlainTextComponentSerializer.plainText().serialize(itemStack.displayName()));

            if (messageType.isEnabled()) {
                player.sendMessage(MessageUtil.build(messageType.getText(), "\\{item\\}", itemName));
            }

            if (actionBarType.isEnabled()) {
                player.sendActionBar(MessageUtil.build(actionBarType.getText(), "\\{item\\}", itemName));
            }

        }

    }

}
