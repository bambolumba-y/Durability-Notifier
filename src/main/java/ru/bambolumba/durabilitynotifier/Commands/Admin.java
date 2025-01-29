package ru.bambolumba.durabilitynotifier.Commands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import ru.bambolumba.durabilitynotifier.Notifications.ActionBarType;
import ru.bambolumba.durabilitynotifier.Notifications.MessageType;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;
import ru.bambolumba.durabilitynotifier.Utils.MessageUtil;

public class Admin {

    private final DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);

    public boolean execute(Player player, String[] args) {

        if (!player.hasPermission("durability.admin")) {
            player.sendMessage(MessageUtil.build("error.common.not-allowed"));
            return true;
        }

        if (args[1].equalsIgnoreCase("reload")) {
            ConfigManager.reloadConfigFiles();
            player.sendMessage(MessageUtil.build("admin.reloaded"));
            return true;
        }

        if (args.length == 3) {

            //./durability admin set {value}
            //set the item durability to the specified amount. I don't know why you might need it.
            if (args[1].equalsIgnoreCase("set")) {

                ItemStack itemStack = player.getInventory().getItemInMainHand();

                if (itemStack.getType() == Material.AIR) {
                    return true;
                }

                Damageable damageable = (Damageable) itemStack.getItemMeta();

                if (!damageable.hasDamageValue()) {
                    player.sendMessage(MessageUtil.build("error.admin.not-damageable-item"));
                    return true;
                }

                int target;
                try {
                    target = Integer.parseInt(args[2]);
                } catch (NumberFormatException exception) {
                    player.sendMessage(MessageUtil.build("error.common.no-number"));
                    return true;
                }

                damageable.setDamage(itemStack.getType().getMaxDurability() - target);
                itemStack.setItemMeta(damageable);
                player.getInventory().setItemInMainHand(itemStack);
                player.sendMessage(MessageUtil.build("admin.durability-changed"));

                return true;
            }

        }

        if (args.length == 2) {

            //./durability admin preview
            //Send the preview of notifications
            if (args[1].equalsIgnoreCase("preview")) {

                ActionBarType actionBarType = plugin.getActionBar();
                MessageType messageType = plugin.getMessage();

                player.sendMessage(ConfigManager.buildMessage(messageType.getText(), true));
                player.sendActionBar(ConfigManager.buildMessage(actionBarType.getText(), true));

                return true;
            }

        }

        return false;
    }

}
