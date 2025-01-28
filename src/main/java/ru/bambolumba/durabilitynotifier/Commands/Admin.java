package ru.bambolumba.durabilitynotifier.Commands;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;
import ru.bambolumba.durabilitynotifier.Utils.MessageUtil;

public class Admin {

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
            if (args[1].equalsIgnoreCase("set")) {

                ItemStack itemStack = player.getInventory().getItemInMainHand();

                if (!(itemStack instanceof Damageable damageable)) {
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

                damageable.setDamage(target);
                itemStack.setItemMeta(damageable);
                player.getInventory().setItemInMainHand(itemStack);
                player.sendMessage(MessageUtil.build("admin.durability-changed"));
            }
        }

        return false;
    }

}
