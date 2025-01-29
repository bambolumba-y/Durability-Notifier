package ru.bambolumba.durabilitynotifier.Utils;

import it.unimi.dsi.fastutil.Pair;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Notifications.ActionBarType;
import ru.bambolumba.durabilitynotifier.Notifications.MessageType;
import ru.bambolumba.durabilitynotifier.Notifications.SoundType;

import java.util.List;

public class DurabilityUtil {

    private final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
    private final MessageType messageType = plugin.getMessage();
    private final ActionBarType actionBarType = plugin.getActionBar();
    private final SoundType soundType = plugin.getSoundType();

    public DurabilityUtil() {

    }

    public int getDurability(ItemStack itemStack, Damageable damageable) {
        return itemStack.getType().getMaxDurability() - damageable.getDamage();
    }

    public boolean isDurabilityLow(int durability) {
        return durability < ConfigManager.getConfig().getInt("notifications.required-durability");
    }

    public void sendDamageNotification(Player player, ItemStack itemStack) {

        Damageable damageable = (Damageable) itemStack.getItemMeta();

        if (!damageable.hasDamageValue()) {
            return;
        }

        int durability = getDurability(itemStack, damageable);

        if (isDurabilityLow(durability)) {

            String itemName = MessageUtil.removeBrackets(PlainTextComponentSerializer.plainText().serialize(itemStack.displayName()));

            List<Pair<String, String>> replacements = List.of(
                    Pair.of("{item}", itemName),
                    Pair.of("{durability}", String.valueOf(durability))
            );

            if (messageType.isEnabled()) {
                player.sendMessage(MessageUtil.build(messageType.getDamageText(), replacements));
            }

            if (actionBarType.isEnabled()) {
                player.sendActionBar(MessageUtil.build(actionBarType.getDamageText(), replacements));
            }

            if (soundType.isEnabled()) {
                player.playSound(player.getLocation(), soundType.getDamageSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
            }

        }

    }

    public void sendBreakNotification(Player player, ItemStack itemStack) {

        Damageable damageable = (Damageable) itemStack.getItemMeta();

        if (!damageable.hasDamageValue()) {
            return;
        }

        int durability = getDurability(itemStack, damageable);

        if (isDurabilityLow(durability)) {

            String itemName = MessageUtil.removeBrackets(PlainTextComponentSerializer.plainText().serialize(itemStack.displayName()));

            List<Pair<String, String>> replacements = List.of(
                    Pair.of("{item}", itemName),
                    Pair.of("{durability}", String.valueOf(0))
            );

            if (messageType.isEnabled()) {
                player.sendMessage(MessageUtil.build(messageType.getBreakText(), replacements));
            }

            if (actionBarType.isEnabled()) {
                player.sendActionBar(MessageUtil.build(actionBarType.getBreakText(), replacements));
            }

            if (soundType.isEnabled()) {
                player.playSound(player.getLocation(), soundType.getBreakSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
            }

        }

    }

}
