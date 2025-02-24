package ru.bambolumba.durabilitynotifier.Utils;

import it.unimi.dsi.fastutil.Pair;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Notifications.ActionBarType;
import ru.bambolumba.durabilitynotifier.Notifications.MessageType;
import ru.bambolumba.durabilitynotifier.Notifications.SoundType;

import java.util.List;
import java.util.Objects;

public class DurabilityUtil {

    private final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
    private final MessageType messageType;
    private final ActionBarType actionBarType;
    private final SoundType soundType;


    public DurabilityUtil() {
        DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
        this.messageType = plugin.getMessage();
        this.actionBarType = plugin.getActionBar();
        this.soundType = plugin.getSound();
    }

    public int getDurability(ItemStack itemStack, Damageable damageable) {
        return itemStack.getType().getMaxDurability() - damageable.getDamage();
    }

    public boolean isDurabilityLow(int durability) {
        return durability < ConfigManager.getConfig().getInt("notifications.required-durability");
    }

    public void sendDamageNotification(Player player, ItemStack itemStack, int durability) {

        Damageable damageable = (Damageable) itemStack.getItemMeta();

        if (!damageable.hasDamage()) {
            return;
        }

        if (isDurabilityLow(durability)) {

            String itemName = getName(itemStack);

            List<Pair<String, String>> replacements = List.of(
                    Pair.of("\\{item\\}", itemName),
                    Pair.of("\\{durability\\}", String.valueOf(durability))
            );


            if (messageType.isEnabled()) {
                if (!messageType.getDamageText().isEmpty()) {
                    player.sendMessage(MessageUtil.build(messageType.getDamageText(), replacements));
                }
            }

            if (actionBarType.isEnabled()) {
                if (!actionBarType.getDamageText().isEmpty()) {
                    player.spigot().sendMessage
                            (ChatMessageType.ACTION_BAR, new TextComponent(MessageUtil.build(actionBarType.getDamageText(), replacements)));
                }
            }

            if (soundType.isEnabled()) {
                if (soundType.getDamageSound() != null) {
                    player.playSound(player.getLocation(), soundType.getDamageSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
            }

        }

    }

    public void sendBreakNotification(Player player, ItemStack itemStack, int durability) {

        Damageable damageable = (Damageable) itemStack.getItemMeta();

        if (!damageable.hasDamage()) {
            return;
        }

        if (isDurabilityLow(durability)) {

            String itemName = getName(itemStack);

            List<Pair<String, String>> replacements = List.of(
                    Pair.of("\\{item\\}", itemName),
                    Pair.of("\\{durability\\}", String.valueOf(durability))
            );

            if (messageType.isEnabled()) {
                if (!messageType.getBreakText().isEmpty()) {
                    player.sendMessage(MessageUtil.build(messageType.getBreakText(), replacements));
                }
            }

            if (actionBarType.isEnabled()) {
                if (!actionBarType.getBreakText().isEmpty()) {
                    player.spigot().sendMessage
                            (ChatMessageType.ACTION_BAR, new TextComponent(MessageUtil.build(actionBarType.getBreakText(), replacements)));
                }
            }

            if (soundType.isEnabled()) {
                if (soundType.getBreakSound() != null) {
                    player.playSound(player.getLocation(), soundType.getBreakSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);
                }
            }

        }

    }

    public String getName(ItemStack itemStack) {

        if (!itemStack.getItemMeta().hasDisplayName()) {
            String name = ConfigManager.getConfig().getString("item-names." + itemStack.getType().name().toLowerCase());
            return Objects.requireNonNullElseGet(name, () -> itemStack.getType().name().replace("_", " ").toLowerCase());
        } else {
            return itemStack.getItemMeta().getDisplayName();
        }

    }

}
