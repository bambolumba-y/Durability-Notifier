package ru.bambolumba.durabilitynotifier.Commands;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import ru.bambolumba.durabilitynotifier.ProjectDurability;
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

                if (itemStack.getType() == Material.AIR) {
                    ProjectDurability.getPlugin(ProjectDurability.class).getLogger().info("No item in hand");
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
            }
        }

        return false;
    }

}
